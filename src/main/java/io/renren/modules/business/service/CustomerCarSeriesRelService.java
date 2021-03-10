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

    /**
     * 分页列表(自动生成)
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据客户id获取车辆列表
     *
     * @param customerId
     * @return
     */
    List<CustomerCarVo> getCarListByCustomerId(Long customerId);

    /**
     * 客户车辆新增
     *
     * @param customerCarVo
     * @return
     */
    boolean saveCustomerCarSeriesRel(CustomerCarVo customerCarVo);

    /**
     * 客户车辆更新
     *
     * @param customerCarSeriesRel
     * @return
     */
    boolean updateCustomerCarSeriesRelById(CustomerCarSeriesRelEntity customerCarSeriesRel);

    /**
     * 客户车辆删除
     *
     * @param asList
     * @return
     */
    boolean removeByCustomerCarSeriesRelIds(List<Long> asList);
}

