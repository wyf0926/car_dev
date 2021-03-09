package io.renren.modules.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import io.renren.common.exception.RRException;
import io.renren.modules.business.dao.OrderMaItemsRelDao;
import io.renren.modules.business.dao.OrderPartRelDao;
import io.renren.modules.business.dao.PartDao;
import io.renren.modules.business.entity.*;
import io.renren.modules.business.vo.OrdersVo;
import io.renren.modules.business.vo.PartVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.business.dao.OrdersDao;
import io.renren.modules.business.service.OrdersService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("orderService")
public class OrdersServiceImpl extends ServiceImpl<OrdersDao, OrdersEntity> implements OrdersService {

    @Resource
    private OrderPartRelDao orderPartRelDao;

    @Resource
    private OrderMaItemsRelDao orderMaItemsRelDao;

    @Resource
    private PartDao partDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrdersEntity> page = this.page(
                new Query<OrdersEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public OrdersVo getOrderDetailById(Long orderId) {
        return this.baseMapper.selectOrderDetailById(orderId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrdersVo saveOrder(OrdersVo order) {
        OrdersEntity ordersEntity = new OrdersEntity();
        PartEntity partEntity;
        BeanUtil.copyProperties(order, ordersEntity);
        //插入维修单基本信息
        if (this.baseMapper.insert(ordersEntity) != 1) {
            throw new RRException("插入维修单表时发现未知异常！");
        }
        //插入相关配件信息
        for (PartVo vo : order.getPartList()) {
            OrderPartRelEntity orderPartRelEntity = new OrderPartRelEntity();
            orderPartRelEntity.setOrderId(order.getOrderId());
            orderPartRelEntity.setPartId(vo.getPartId());
            orderPartRelEntity.setUsedQuantity(vo.getUsedQuantity());
            orderPartRelEntity.setRealPrice(vo.getUnitPrice());
            orderPartRelEntity.setTotalAmount(orderPartRelEntity.getRealPrice().multiply(vo.getUsedQuantity()));
            orderPartRelEntity.setComment(vo.getComment());
            if (this.orderPartRelDao.insert(orderPartRelEntity) != 1) {
                throw new RRException("插入配件表时发现未知异常！");
            }
            //更新库存数量
            partEntity = partDao.selectById(vo.getPartId());
            partEntity.setAmount(partEntity.getAmount().subtract(vo.getUsedQuantity()));
            partDao.updateById(partEntity);
            if (partEntity.getAmount().compareTo(BigDecimal.ZERO) < 0) {
                throw new RRException("库存数量不足！");
            }
        }
        //插入相关维修项目
        for (OrderMaItemsRelEntity orderMaItemsRelEntity : order.getOrderMaItemsRelEntities()) {
            if (this.orderMaItemsRelDao.insert(orderMaItemsRelEntity) != 1) {
                throw new RRException("插入维修项目表时发现未知异常！");
            }
        }
        return order;
    }

}