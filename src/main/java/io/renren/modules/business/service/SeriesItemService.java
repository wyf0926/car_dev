package io.renren.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.business.entity.SeriesItemEntity;

import java.util.List;
import java.util.Map;

/**
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-04 16:31:29
 */
public interface SeriesItemService extends IService<SeriesItemEntity> {

    /**
     * 车款分页列表
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 车款保存
     *
     * @param seriesItem
     * @return
     */
    boolean saveSeriesItem(SeriesItemEntity seriesItem);

    /**
     * 车款更新
     *
     * @param seriesItem
     * @return
     */
    boolean updateSeriesItem(SeriesItemEntity seriesItem);

    /**
     * 车款删除
     *
     * @param itemIdList
     * @return
     */
    boolean removeByItemIds(List<Long> itemIdList);
}

