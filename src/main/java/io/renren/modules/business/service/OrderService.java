package io.renren.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.business.entity.OrderEntity;

import java.util.Map;

/**
 * 维修单表

 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:31
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

