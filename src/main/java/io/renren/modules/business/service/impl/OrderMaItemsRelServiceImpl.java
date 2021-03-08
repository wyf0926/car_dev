package io.renren.modules.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.business.dao.OrderMaItemsRelDao;
import io.renren.modules.business.entity.OrderMaItemsRelEntity;
import io.renren.modules.business.service.OrderMaItemsRelService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("orderMaItemsRelService")
public class OrderMaItemsRelServiceImpl extends ServiceImpl<OrderMaItemsRelDao, OrderMaItemsRelEntity> implements OrderMaItemsRelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderMaItemsRelEntity> page = this.page(
                new Query<OrderMaItemsRelEntity>().getPage(params),
                new QueryWrapper<OrderMaItemsRelEntity>()
        );

        return new PageUtils(page);
    }

}