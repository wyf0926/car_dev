package io.renren.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.business.entity.CustomerEntity;

import java.util.Map;

/**
 * 客户表

 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:30
 */
public interface CustomerService extends IService<CustomerEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

