package io.renren.modules.business.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.business.dao.SeriesItemDao;
import io.renren.modules.business.entity.SeriesItemEntity;
import io.renren.modules.business.service.SeriesItemService;


@Service("seriesItemService")
public class SeriesItemServiceImpl extends ServiceImpl<SeriesItemDao, SeriesItemEntity> implements SeriesItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeriesItemEntity> page = this.page(
                new Query<SeriesItemEntity>().getPage(params),
                new QueryWrapper<SeriesItemEntity>()
        );

        return new PageUtils(page);
    }

}