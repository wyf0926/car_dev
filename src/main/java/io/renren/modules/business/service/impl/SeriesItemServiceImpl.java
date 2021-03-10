package io.renren.modules.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.business.dao.SeriesDao;
import io.renren.modules.business.dao.SeriesItemDao;
import io.renren.modules.business.entity.SeriesEntity;
import io.renren.modules.business.entity.SeriesItemEntity;
import io.renren.modules.business.service.SeriesItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
        if (list.size() > 0) {
            throw new RRException("错误:该车系下已有该车款,请勿重复创建!", 501);
        }

        if (1 != this.baseMapper.insert(seriesItem)) {
            throw new RRException("错误:新增时发生未知异常,请联系系统管理员!", 501);
        }

        if (!refreshPriceRange(seriesItem.getSeriesId())) {
            throw new RRException("错误:发生未知异常,请联系系统管理员!", 501);
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateSeriesItem(SeriesItemEntity seriesItem) {
        List<SeriesItemEntity> list = this.baseMapper.selectList(
                new QueryWrapper<SeriesItemEntity>().lambda()
                        .eq(SeriesItemEntity::getSpecName, seriesItem.getSpecName())
                        .eq(SeriesItemEntity::getSeriesId, seriesItem.getSeriesId())
                        .ne(SeriesItemEntity::getItemId, seriesItem.getItemId()));
        if (list.size() > 0) {
            throw new RRException("错误:该车系下已有该车款,请勿重复创建!", 501);
        }

        if (1 != this.baseMapper.updateById(seriesItem)) {
            throw new RRException("错误:更新时发生未知异常,请联系系统管理员!", 501);
        }

        if (!refreshPriceRange(seriesItem.getSeriesId())) {
            throw new RRException("错误:发生未知异常,请联系系统管理员!", 501);
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeByItemIds(List<Long> itemIdList) {
        Long seriesId = this.baseMapper.selectById(itemIdList.get(0)).getSeriesId();
        int delCount = this.baseMapper.deleteBatchIds(itemIdList);
        // 重新计算价格区间
        if (itemIdList.get(0) != null) {
            if (!refreshPriceRange(seriesId)) {
                throw new RRException("错误:发生未知异常,请联系系统管理员!", 501);
            }
        }
        return delCount == itemIdList.size();
    }

    /**
     * 刷新价格区间
     *
     * @param seriesId
     * @return
     */
    private Boolean refreshPriceRange(Long seriesId) {
        SeriesEntity seriesEntity = seriesDao.selectById(seriesId);
        List<BigDecimal> priceList = this.baseMapper.selectObjs(
                new LambdaQueryWrapper<SeriesItemEntity>()
                        .select(SeriesItemEntity::getReferPrice)
                        .eq(SeriesItemEntity::getSeriesId, seriesId))
                .stream()
                .map(o -> (BigDecimal) o)
                .collect(Collectors.toList());
        if (priceList.size() == 0) {
            seriesEntity.setSeriesFctMinPrice(BigDecimal.ZERO);
            seriesEntity.setSeriesFctMaxPrice(BigDecimal.ZERO);
        } else {
            seriesEntity.setSeriesFctMaxPrice(Collections.max(priceList));
            seriesEntity.setSeriesFctMinPrice(Collections.min(priceList));
        }
        return seriesDao.updateById(seriesEntity) == 1;
    }

}