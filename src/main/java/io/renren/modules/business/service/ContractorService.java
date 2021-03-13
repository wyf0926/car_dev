package io.renren.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.business.entity.ContractorEntity;

import java.util.Map;

/**
 * 承修方表
 *
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-02-26 15:59:13
 */
public interface ContractorService extends IService<ContractorEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean saveContractor(ContractorEntity contractor);

    boolean updateContractorById(ContractorEntity contractor);
}

