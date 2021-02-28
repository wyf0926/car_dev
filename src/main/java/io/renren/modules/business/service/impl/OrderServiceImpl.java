package io.renren.modules.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import io.renren.common.exception.RRException;
import io.renren.modules.business.dao.OrderMaItemsRelDao;
import io.renren.modules.business.dao.OrderPartRelDao;
import io.renren.modules.business.entity.*;
import io.renren.modules.business.vo.OrdersVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.business.dao.OrderDao;
import io.renren.modules.business.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrdersEntity> implements OrderService {

    @Resource
    private OrderPartRelDao orderPartRelDao;

    @Resource
    private OrderMaItemsRelDao orderMaItemsRelDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrdersEntity> page = this.page(
                new Query<OrdersEntity>().getPage(params),
                new QueryWrapper<OrdersEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public OrdersVo getOrderDetailById(Long orderId) {
        return this.baseMapper.selectOrderDetailById(orderId);
    }

    @Override
    @Transactional
    public OrdersVo saveOrder(OrdersVo order) {
        OrdersEntity ordersEntity = new OrdersEntity();
        BeanUtil.copyProperties(order, ordersEntity);
        //插入维修单基本信息
        if (this.baseMapper.insert(ordersEntity) != 1)
            throw new RRException("插入维修单表时发现未知异常！");
        //插入相关配件信息
        for (PartEntity partEntity : order.getPartList()) {
            OrderPartRelEntity orderPartRelEntity = new OrderPartRelEntity();
            orderPartRelEntity.setOrderId(order.getOrderId());
            orderPartRelEntity.setPartId(partEntity.getPartId());
            orderPartRelEntity.setUsedQuantity(partEntity.getAmount().intValue());
            orderPartRelEntity.setRealPrice(partEntity.getUnitPrice());
            orderPartRelEntity.setTotalAmount(orderPartRelEntity.getRealPrice().multiply(new BigDecimal(orderPartRelEntity.getUsedQuantity())));
            orderPartRelEntity.setComment(partEntity.getComment());
            if (this.orderPartRelDao.insert(orderPartRelEntity) != 1)
                throw new RRException("插入配件表时发现未知异常！");
        }
        //插入相关维修项目
        for (OrderMaItemsRelEntity orderMaItemsRelEntity : order.getOrderMaItemsRelEntities()) {
            if (this.orderMaItemsRelDao.insert(orderMaItemsRelEntity) != 1)
                throw new RRException("插入维修项目表时发现未知异常！");
        }
        return order;
    }

}