package io.renren.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.business.entity.CustomerCarSeriesRelEntity;
import io.renren.modules.business.vo.CustomerCarVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-09 11:29:48
 */
public interface CustomerCarSeriesRelService extends IService<CustomerCarSeriesRelEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CustomerCarVo> getCarListByCustomerId(Long customerId);

    boolean saveCustomerCarSeriesRel(CustomerCarSeriesRelEntity customerCarSeriesRel);

    boolean removeByCustomerCarSeriesRelIds(List<Long> asList);

    boolean updateCustomerCarSeriesRelById(CustomerCarSeriesRelEntity customerCarSeriesRel);
}

