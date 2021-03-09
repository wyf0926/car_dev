package io.renren.modules.business.service.impl;

import io.renren.modules.business.vo.CustomerCarVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.business.dao.CustomerCarSeriesRelDao;
import io.renren.modules.business.entity.CustomerCarSeriesRelEntity;
import io.renren.modules.business.service.CustomerCarSeriesRelService;


@Service("customerCarSeriesRelService")
public class CustomerCarSeriesRelServiceImpl extends ServiceImpl<CustomerCarSeriesRelDao, CustomerCarSeriesRelEntity> implements CustomerCarSeriesRelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CustomerCarSeriesRelEntity> page = this.page(
                new Query<CustomerCarSeriesRelEntity>().getPage(params),
                new QueryWrapper<CustomerCarSeriesRelEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CustomerCarVo> getCarListByCustomerId(Long customerId) {

        return this.baseMapper.selectCarListByCustomerId(customerId);
    }

}