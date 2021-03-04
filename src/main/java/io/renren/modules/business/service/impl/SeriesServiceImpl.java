package io.renren.modules.business.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.business.dao.SeriesDao;
import io.renren.modules.business.entity.SeriesEntity;
import io.renren.modules.business.service.SeriesService;


@Service("seriesService")
public class SeriesServiceImpl extends ServiceImpl<SeriesDao, SeriesEntity> implements SeriesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeriesEntity> page = this.page(
                new Query<SeriesEntity>().getPage(params),
                new QueryWrapper<SeriesEntity>()
        );

        return new PageUtils(page);
    }

}