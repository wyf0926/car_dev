package io.renren.modules.business.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.business.dao.PartDao;
import io.renren.modules.business.entity.PartEntity;
import io.renren.modules.business.service.PartService;


@Service("partService")
public class PartServiceImpl extends ServiceImpl<PartDao, PartEntity> implements PartService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PartEntity> page = this.page(
                new Query<PartEntity>().getPage(params),
                new QueryWrapper<PartEntity>()
        );

        return new PageUtils(page);
    }

}