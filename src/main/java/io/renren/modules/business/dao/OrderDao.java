package io.renren.modules.business.dao;

import io.renren.modules.business.entity.OrdersEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.business.vo.OrdersVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 维修单表

 * 
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-02-28 13:30:42
 */
@Mapper
public interface OrderDao extends BaseMapper<OrdersEntity> {
    /**
     * 返回带配件详情的订单信息
     * @param orderId
     * @return
     */
    OrdersVo selectOrderDetailById(@Param("orderId") Long orderId);
}
