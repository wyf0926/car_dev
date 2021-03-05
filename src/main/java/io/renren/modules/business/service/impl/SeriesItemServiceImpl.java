package io.renren.modules.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.renren.common.exception.RRException;
import io.renren.modules.business.dao.SeriesDao;
import io.renren.modules.business.entity.SeriesEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.business.dao.SeriesItemDao;
import io.renren.modules.business.entity.SeriesItemEntity;
import io.renren.modules.business.service.SeriesItemService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("seriesItemService")
public class SeriesItemServiceImpl extends ServiceImpl<SeriesItemDao, SeriesItemEntity> implements SeriesItemService {

    @Resource
    private SeriesDao seriesDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeriesItemEntity> page = this.page(
                new Query<SeriesItemEntity>().getPage(params),
                new QueryWrapper<SeriesItemEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveSeriesItem(SeriesItemEntity seriesItem) {
        List<SeriesItemEntity> list = this.baseMapper.selectList(
                new QueryWrapper<SeriesItemEntity>().lambda()
                        .eq(SeriesItemEntity::getSpecName, seriesItem.getSpecName())
                        .eq(SeriesItemEntity::getSeriesId, seriesItem.getSeriesId()));
        if(list.size() > 0){
            throw new RRException("错误:该车系下已有该车款,请勿重复创建!",501);
        }
        if(!refreshPriceRange(seriesItem)){
            throw new RRException("错误:发生位置异常,请联系系统管理员!",501);
        }
        return this.baseMapper.insert(seriesItem) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateSeriesItem(SeriesItemEntity seriesItem) {
        List<SeriesItemEntity> list = this.baseMapper.selectList(
                new QueryWrapper<SeriesItemEntity>().lambda()
                        .eq(SeriesItemEntity::getSpecName, seriesItem.getSpecName())
                        .eq(SeriesItemEntity::getSeriesId, seriesItem.getSeriesId())
                        .ne(SeriesItemEntity::getItemId,seriesItem.getItemId()));
        if(list.size() > 0){
            throw new RRException("错误:该车系下已有该车款,请勿重复创建!",501);
        }

        List<SeriesItemEntity> itemList = this.baseMapper.selectList(
                new QueryWrapper<SeriesItemEntity>().lambda()
                        .eq(SeriesItemEntity::getSeriesId, seriesItem.getSeriesId()));

        if(itemList.size() == 1){
            BigDecimal referPrice = seriesItem.getReferPrice();
            SeriesEntity seriesEntity = seriesDao.selectById(seriesItem.getSeriesId());
            seriesEntity.setSeriesFctMaxPrice(referPrice);
            seriesEntity.setSeriesFctMinPrice(referPrice);
            if(1 != seriesDao.updateById(seriesEntity)){
                throw new RRException("错误:发生未知异常,请联系系统管理员!",501);
            }
        }else {
            if(!refreshPriceRange(seriesItem)){
                throw new RRException("错误:发生未知异常,请联系系统管理员!",501);
            }
        }

        return this.baseMapper.updateById(seriesItem) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeByItemIds(List<Long> itemIdList) {
        Long seriesId = this.baseMapper.selectById(itemIdList.get(0)).getSeriesId();
        int delCount = this.baseMapper.deleteBatchIds(itemIdList);
        // 重新计算价格区间
        if(itemIdList.get(0) != null){
            SeriesEntity seriesEntity = seriesDao.selectById(seriesId);
            List<BigDecimal> priceList = this.baseMapper.selectObjs(
                    new LambdaQueryWrapper<SeriesItemEntity>()
                            .select(SeriesItemEntity::getReferPrice)
                            .eq(SeriesItemEntity::getSeriesId, seriesId))
                    .stream()
                    .map(o -> (BigDecimal) o)
                    .collect(Collectors.toList());
            if (priceList.size() == 0){
                seriesEntity.setSeriesFctMinPrice(BigDecimal.ZERO);
                seriesEntity.setSeriesFctMaxPrice(BigDecimal.ZERO);
            }else {
                seriesEntity.setSeriesFctMaxPrice(Collections.max(priceList));
                seriesEntity.setSeriesFctMinPrice(Collections.min(priceList));
            }
            if(1 != seriesDao.updateById(seriesEntity)){
                throw new RRException("错误:发生未知异常,请联系系统管理员!",501);
            }
        }
        return delCount == itemIdList.size();
    }

    /**
     * 刷新价格区间
     *
     * @param seriesItem
     * @return
     */
    private Boolean refreshPriceRange(SeriesItemEntity seriesItem) {
        BigDecimal referPrice = seriesItem.getReferPrice();

        if(!(BigDecimal.ZERO).equals(referPrice)){
            SeriesEntity seriesEntity = seriesDao.selectById(seriesItem.getSeriesId());
            BigDecimal max = seriesEntity.getSeriesFctMaxPrice();
            BigDecimal min = seriesEntity.getSeriesFctMinPrice();
            if(referPrice.compareTo(max) == 1){
                seriesEntity.setSeriesFctMaxPrice(referPrice);
            }
            if((BigDecimal.ZERO).equals(min)){
                seriesEntity.setSeriesFctMinPrice(referPrice);
            }else{
                if(referPrice.compareTo(min) == -1){
                    seriesEntity.setSeriesFctMinPrice(referPrice);
                }
            }
            return seriesDao.updateById(seriesEntity) == 1;
        }
        return true;
    }
}