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

    /**
     * 客户分页列表接口
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 客户保存
     *
     * @param customer
     * @return
     */
    boolean saveCustomer(CustomerEntity customer);

    /**
     * 客户更新
     *
     * @param customer
     * @return
     */
    boolean updateCustomerById(CustomerEntity customer);
}

