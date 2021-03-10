package io.renren.modules.business.service.impl;

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
import io.renren.modules.business.service.SeriesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-04 16:31:29
 */
@Service("seriesService")
public class SeriesServiceImpl extends ServiceImpl<SeriesDao, SeriesEntity> implements SeriesService {

    @Resource
    private SeriesItemDao seriesItemDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<SeriesEntity> seriesEntityQueryWrapper = new QueryWrapper<>();

        // 解析查询参数
        Object nameObj = params.get("seriesName");
        if (nameObj != null) {
            String seriesName = nameObj.toString();
            if (StringUtils.isNotBlank(seriesName)) {
                seriesEntityQueryWrapper.lambda().like(SeriesEntity::getSeriesName, seriesName);
            }
        }

        Object typeObj = params.get("carType");
        if (typeObj != null) {
            String carType = typeObj.toString();
            if (StringUtils.isNotBlank(carType)) {
                String[] types = carType.split(",");
                seriesEntityQueryWrapper.lambda().in(SeriesEntity::getLevelName, types);
            }
        }

        IPage<SeriesEntity> page = this.page(
                new Query<SeriesEntity>().getPage(params),
                seriesEntityQueryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public boolean insertSeries(SeriesEntity series) {
        List<SeriesEntity> list = this.baseMapper.selectList(new QueryWrapper<SeriesEntity>()
                .lambda().eq(SeriesEntity::getSeriesName, series.getSeriesName()));
        if (list.size() > 0) {
            throw new RRException("错误:该车系名已存在,请勿重复创建!", 501);
        }
        return this.baseMapper.insert(series) == 1;
    }

    @Override
    public boolean updateSeriesById(SeriesEntity series) {
        List<SeriesEntity> list = this.baseMapper.selectList(
                new QueryWrapper<SeriesEntity>().lambda()
                        .eq(SeriesEntity::getSeriesName, series.getSeriesName())
                        .ne(SeriesEntity::getSeriesId, series.getSeriesId()));
        if (list.size() > 0) {
            throw new RRException("错误:该车系名已存在,请勿重复创建!", 501);
        };
        return this.baseMapper.updateById(series) == 1;
    }

    @Override
    public boolean removeSeriesByIds(List<Long> seriesIds) {

        List<SeriesItemEntity> list = seriesItemDao.selectList(
                new QueryWrapper<SeriesItemEntity>().lambda()
                        .in(SeriesItemEntity::getSeriesId, seriesIds));
        if (list.size() > 0) {
            throw new RRException("删除失败,请先删除该车系下所有车款!", 501);
        }

        return this.baseMapper.deleteBatchIds(seriesIds) == seriesIds.size();
    }

}