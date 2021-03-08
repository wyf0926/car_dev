package io.renren.modules.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.business.entity.OrderMaItemsRelEntity;
import io.renren.modules.business.entity.OrdersEntity;
import io.renren.modules.business.vo.OrdersVo;
import io.renren.modules.business.vo.PartVo;
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
     *
     * @param orderId
     * @return
     */
    OrdersVo selectOrderDetailById(@Param("orderId") Long orderId);

    /**
     * 根据维修单id查询配件详情
     *
     * @param orderId 维修单id
     * @return 配件详情
     */
    PartVo selectPartDetailByOrderId(@Param("orderId") Long orderId);

    /**
     * 根据维修单ID查询维修项目详情
     *
     * @param orderId 维修单id
     * @return 维修项目详情
     */
    OrderMaItemsRelEntity selectItemDetailByOrderId(@Param("orderId") Long orderId);
}
