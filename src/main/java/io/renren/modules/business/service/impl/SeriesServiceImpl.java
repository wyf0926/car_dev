package io.renren.modules.business.service.impl;

import org.apache.commons.lang.StringUtils;
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

        QueryWrapper<SeriesEntity> seriesEntityQueryWrapper = new QueryWrapper<>();

        // 解析查询参数
        Object nameObj = params.get("seriesName");
        if(nameObj != null){
            String seriesName = nameObj.toString();
            if(StringUtils.isNotBlank(seriesName)){
                seriesEntityQueryWrapper.lambda().like(SeriesEntity::getSeriesName,seriesName);
            }
        }

        Object typeObj = params.get("carType");
        if(typeObj != null){
            String carType = typeObj.toString();
            if(StringUtils.isNotBlank(carType)){
                String[] types = carType.split(",");
                seriesEntityQueryWrapper.lambda().in(SeriesEntity::getLevelName,types);
            }
        }

        IPage<SeriesEntity> page = this.page(
                new Query<SeriesEntity>().getPage(params),
                seriesEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}