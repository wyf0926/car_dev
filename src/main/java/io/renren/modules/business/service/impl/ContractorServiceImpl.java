package io.renren.modules.business.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.business.dao.ContractorDao;
import io.renren.modules.business.entity.ContractorEntity;
import io.renren.modules.business.service.ContractorService;


@Service("contractorService")
public class ContractorServiceImpl extends ServiceImpl<ContractorDao, ContractorEntity> implements ContractorService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ContractorEntity> page = this.page(
                new Query<ContractorEntity>().getPage(params),
                new QueryWrapper<ContractorEntity>()
        );

        return new PageUtils(page);
    }

}