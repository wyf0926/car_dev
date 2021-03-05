package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysDictDao;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.service.SysDictService;


@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements SysDictService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<SysDictEntity> sysDictEntityQueryWrapper = new QueryWrapper<>();

        Object nameObj = params.get("dictName");
        if(nameObj != null){
            String dictName = nameObj.toString();
            if(StringUtils.isNotBlank(dictName)){
                sysDictEntityQueryWrapper.lambda().like(SysDictEntity::getDictName,dictName);
            }
        }

        Object codeObj = params.get("dictCode");
        if(codeObj != null){
            String dictCode = codeObj.toString();
            if(StringUtils.isNotBlank(dictCode)){
                sysDictEntityQueryWrapper.lambda().like(SysDictEntity::getDictCode,dictCode);
            }
        }

        IPage<SysDictEntity> page = this.page(
                new Query<SysDictEntity>().getPage(params),
                sysDictEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}