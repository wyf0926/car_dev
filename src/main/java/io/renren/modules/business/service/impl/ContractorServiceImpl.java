package io.renren.modules.business.service.impl;

import io.renren.common.exception.RRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.business.dao.ContractorDao;
import io.renren.modules.business.entity.ContractorEntity;
import io.renren.modules.business.service.ContractorService;

import javax.annotation.Resource;


@Service("contractorService")
public class ContractorServiceImpl extends ServiceImpl<ContractorDao, ContractorEntity> implements ContractorService {

    @Resource
    private ContractorService contractorService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<ContractorEntity> contractorEntityQueryWrapper = new QueryWrapper<ContractorEntity>();

        Object nameObj = params.get("name");
        if(nameObj != null){
            String name = nameObj.toString();
            if(StringUtils.isNotBlank(name)){
                contractorEntityQueryWrapper.lambda().like(ContractorEntity::getContractorName,name);
            }
        }
        IPage<ContractorEntity> page = this.page(
                new Query<ContractorEntity>().getPage(params),
                contractorEntityQueryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public boolean saveContractor(ContractorEntity contractor) {
        List<ContractorEntity> list = contractorService.list(
                new QueryWrapper<ContractorEntity>()
                        .lambda()
                        .eq(ContractorEntity::getContractorName, contractor.getContractorName()));

        if (list.size() > 0) {
            throw new RRException("错误:该单位名称已创建,请勿重复创建!", 501);
        }
        return this.baseMapper.insert(contractor) == 1;
    }

    @Override
    public boolean updateContractorById(ContractorEntity contractor) {
        List<ContractorEntity> list = contractorService.list(
                new QueryWrapper<ContractorEntity>()
                        .lambda()
                        .eq(ContractorEntity::getContractorName, contractor.getContractorName())
                        .ne(ContractorEntity::getContractorId, contractor.getContractorId())
        );

        if (list.size() > 0) {
            throw new RRException("错误:该单位名称已被使用!", 501);
        }
        return this.baseMapper.updateById(contractor) == 1;
    }

}