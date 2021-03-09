package io.renren.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.business.entity.OrdersEntity;
import io.renren.modules.business.vo.OrdersVo;

import java.util.Map;

/**
 * 维修单表
 *
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-02-28 13:30:42
 */
public interface OrdersService extends IService<OrdersEntity> {

    PageUtils queryPage(Map<String, Object> params);

    OrdersVo getOrderDetailById(Long orderId);

    OrdersVo saveOrder(OrdersVo order);
}

