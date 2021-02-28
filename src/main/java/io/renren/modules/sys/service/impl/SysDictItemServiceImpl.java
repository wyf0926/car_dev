package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysDictItemDao;
import io.renren.modules.sys.entity.SysDictItemEntity;
import io.renren.modules.sys.service.SysDictItemService;


@Service("sysDictItemService")
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemDao, SysDictItemEntity> implements SysDictItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysDictItemEntity> page = this.page(
                new Query<SysDictItemEntity>().getPage(params),
                new QueryWrapper<SysDictItemEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryItemPage(Map<String, Object> params) {
        Long dictId = Long.valueOf(params.get("dictId").toString());

        IPage<SysDictItemEntity> page = this.page(
                new Query<SysDictItemEntity>().getPage(params),
                new QueryWrapper<SysDictItemEntity>().lambda().eq(SysDictItemEntity::getDictId,dictId)
        );

        return new PageUtils(page);
    }

}