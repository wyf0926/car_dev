package io.renren.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.business.entity.SeriesEntity;

import java.util.Map;

/**
 * 
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-04 16:31:29
 */
public interface SeriesService extends IService<SeriesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

