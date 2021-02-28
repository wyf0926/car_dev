package io.renren.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.business.entity.MaItemsEntity;

import java.util.Map;

/**
 * 维修项目表
 *
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-02-26 15:59:13
 */
public interface MaItemsService extends IService<MaItemsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
