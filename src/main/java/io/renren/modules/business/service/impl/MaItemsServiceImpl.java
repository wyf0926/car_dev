package io.renren.modules.business.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.business.dao.MaItemsDao;
import io.renren.modules.business.entity.MaItemsEntity;
import io.renren.modules.business.service.MaItemsService;


@Service("maItemsService")
public class MaItemsServiceImpl extends ServiceImpl<MaItemsDao, MaItemsEntity> implements MaItemsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MaItemsEntity> page = this.page(
                new Query<MaItemsEntity>().getPage(params),
                new QueryWrapper<MaItemsEntity>()
        );

        return new PageUtils(page);
    }

}