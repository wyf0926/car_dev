package io.renren.modules.business.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.exception.RRException;
import io.renren.modules.business.entity.SeriesItemEntity;
import io.renren.modules.business.service.SeriesItemService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.business.entity.SeriesEntity;
import io.renren.modules.business.service.SeriesService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.annotation.Resource;


/**
 * 
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-04 16:31:29
 */
@RestController
@RequestMapping("business/series")
public class SeriesController extends AbstractController {
    @Autowired
    private SeriesService seriesService;
    @Resource
    private SeriesItemService seriesItemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:series:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seriesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{seriesId}")
    @RequiresPermissions("business:series:info")
    public R info(@PathVariable("seriesId") Long seriesId){
		SeriesEntity series = seriesService.getById(seriesId);

        return R.ok().put("series", series);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:series:save")
    public R save(@RequestBody SeriesEntity series){
        List<SeriesEntity> list = seriesService.list(new QueryWrapper<SeriesEntity>()
                .lambda().eq(SeriesEntity::getSeriesName, series.getSeriesName()));
        if(list.size() > 0){
            throw new RRException("错误:该车系名已存在,请勿重复创建!",501);
        }
        series.setSeriesFctMaxPrice(BigDecimal.ZERO);
        series.setSeriesFctMinPrice(BigDecimal.ZERO);
        series.setCreateUser(this.getUserId());
        series.setCreateTime(new Date());
        seriesService.save(series);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:series:update")
    public R update(@RequestBody SeriesEntity series){
        List<SeriesEntity> list = seriesService.list(
                new QueryWrapper<SeriesEntity>().lambda()
                .eq(SeriesEntity::getSeriesName, series.getSeriesName())
                .ne(SeriesEntity::getSeriesId,series.getSeriesId()));
        if(list.size() > 0){
            throw new RRException("错误:该车系名已存在,请勿重复创建!",501);
        }
        series.setModifyUser(this.getUserId());
        series.setModifyTime(new Date());
		seriesService.updateById(series);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:series:delete")
    public R delete(@RequestBody Long[] seriesIds){
        List<Long> ids = Arrays.asList(seriesIds);
        List<SeriesItemEntity> list = seriesItemService.list(
                new QueryWrapper<SeriesItemEntity>().lambda()
                        .in(SeriesItemEntity::getSeriesId, ids));
        if(list.size() > 0){
            throw new RRException("删除失败,请先删除该车系下所有车款!",501);
        }

        seriesService.removeByIds(Arrays.asList(seriesIds));

        return R.ok();
    }

}
