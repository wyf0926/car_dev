package io.renren.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.business.entity.SeriesItemEntity;

import java.util.Map;

/**
 * 
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-04 16:31:29
 */
public interface SeriesItemService extends IService<SeriesItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

