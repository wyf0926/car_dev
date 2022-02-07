package io.renren.modules.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import io.renren.common.exception.RRException;
import io.renren.modules.business.dao.*;
import io.renren.modules.business.entity.*;
import io.renren.modules.business.vo.MaItemVo;
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

import io.renren.modules.business.service.OrdersService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("ordersService")
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
            throw new RRException("插入维修单表时发现未知异常！", 501);
        }
        //插入相关配件信息
        if (0 == order.getPartList().size()) {
            throw new RRException("请至少添加一个配件！", 501);
        }
        for (PartVo vo : order.getPartList()) {
            OrderPartRelEntity orderPartRelEntity = new OrderPartRelEntity();
            orderPartRelEntity.setOrderId(order.getOrderId());
            orderPartRelEntity.setPartName(vo.getName());
            orderPartRelEntity.setPartUnit(vo.getUnit());
            orderPartRelEntity.setUsedQuantity(vo.getUsedQuantity());
            orderPartRelEntity.setRealPrice(vo.getUnitPrice());
            orderPartRelEntity.setTotalAmount(orderPartRelEntity.getRealPrice().multiply(vo.getUsedQuantity()));
            orderPartRelEntity.setComment(vo.getComment());
            if (this.orderPartRelDao.insert(orderPartRelEntity) != 1) {
                throw new RRException("插入配件表时发现未知异常！", 501);
            }
            //更新库存数量
            partEntity = partDao.selectById(vo.getPartId());
            partEntity.setAmount(partEntity.getAmount().subtract(vo.getUsedQuantity()));
            partDao.updateById(partEntity);
            if (partEntity.getAmount().compareTo(BigDecimal.ZERO) < 0) {
                throw new RRException("库存数量不足！", 501);
            }
        }
        //插入相关维修项目
        if (0 == order.getMaItemList().size()) {
            throw new RRException("请至少添加一个维修项目！", 501);
        }
        OrderMaItemsRelEntity orderMaItemsRelEntity = new OrderMaItemsRelEntity();
        for (MaItemVo maItemVo : order.getMaItemList()) {
            orderMaItemsRelEntity.setOrderId(order.getOrderId());
            orderMaItemsRelEntity.setManHour(maItemVo.getManHour());
            orderMaItemsRelEntity.setMaItem(maItemVo.getItemName());
            orderMaItemsRelEntity.setRealPrice(maItemVo.getRealPrice());
            orderMaItemsRelEntity.setTotalAmount(maItemVo.getTotalAmount());
            if (1 != this.orderMaItemsRelDao.insert(orderMaItemsRelEntity)) {
                throw new RRException("添加改维修单相关维修项目时发生异常！", 501);
            }
        }
        return order;
    }

}