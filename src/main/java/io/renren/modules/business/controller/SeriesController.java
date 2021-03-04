package io.renren.modules.business.controller;

import java.util.Arrays;
import java.util.Map;

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



/**
 * 
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-04 16:31:29
 */
@RestController
@RequestMapping("business/series")
public class SeriesController {
    @Autowired
    private SeriesService seriesService;

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
		seriesService.save(series);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:series:update")
    public R update(@RequestBody SeriesEntity series){
		seriesService.updateById(series);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:series:delete")
    public R delete(@RequestBody Long[] seriesIds){
		seriesService.removeByIds(Arrays.asList(seriesIds));

        return R.ok();
    }

}
