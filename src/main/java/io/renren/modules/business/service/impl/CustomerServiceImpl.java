package io.renren.modules.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.business.dao.CustomerDao;
import io.renren.modules.business.entity.CustomerEntity;
import io.renren.modules.business.service.CustomerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("customerService")
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, CustomerEntity> implements CustomerService {

    /**
     * 客户分页列表接口
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<CustomerEntity> queryWrapper = new QueryWrapper<>();

        String key = params.get("key").toString();
        if (StringUtils.isNotBlank(key)) {
            queryWrapper.lambda().like(CustomerEntity::getName, key);
        }

        String t = params.get("type").toString();
        if (StringUtils.isNotBlank(t)) {
            Integer type = Integer.valueOf(t);
            if (type != null) {
                queryWrapper.lambda().eq(CustomerEntity::getType, type);
            }
        }

        IPage<CustomerEntity> page = this.page(
                new Query<CustomerEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

}