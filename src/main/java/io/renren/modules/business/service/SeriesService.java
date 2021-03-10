package io.renren.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.business.entity.SeriesEntity;

import java.util.List;
import java.util.Map;

/**
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-04 16:31:29
 */
public interface SeriesService extends IService<SeriesEntity> {

    /**
     * 车系分页列表查询
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 车系新增
     *
     * @param series
     * @return
     */
    boolean insertSeries(SeriesEntity series);

    /**
     * 车系修改
     *
     * @param series
     * @return
     */
    boolean updateSeriesById(SeriesEntity series);

    /**
     * 车系删除
     *
     * @param seriesIds
     * @return
     */
    boolean removeSeriesByIds(List<Long> seriesIds);
}

