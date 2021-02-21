package io.renren.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.business.entity.OrderPartRelEntity;

import java.util.Map;

/**
 * 订单配件关系表（详情表）
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:31
 */
public interface OrderPartRelService extends IService<OrderPartRelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

