package io.renren.modules.business.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.business.dao.OrderPartRelDao;
import io.renren.modules.business.entity.OrderPartRelEntity;
import io.renren.modules.business.service.OrderPartRelService;


@Service("orderPartRelService")
public class OrderPartRelServiceImpl extends ServiceImpl<OrderPartRelDao, OrderPartRelEntity> implements OrderPartRelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderPartRelEntity> page = this.page(
                new Query<OrderPartRelEntity>().getPage(params),
                new QueryWrapper<OrderPartRelEntity>()
        );

        return new PageUtils(page);
    }

}