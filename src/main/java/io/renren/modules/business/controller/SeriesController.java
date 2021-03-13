package io.renren.modules.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.business.entity.SeriesEntity;
import io.renren.modules.business.entity.SeriesItemEntity;
import io.renren.modules.business.service.SeriesItemService;
import io.renren.modules.business.service.SeriesService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-04 16:31:29
 */
@RestController
@RequestMapping("business/series")
public class SeriesController extends AbstractController {
    @Autowired
    private SeriesService seriesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:series:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = seriesService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @GetMapping("/listAll")
    @RequiresPermissions("business:series:list")
    public R listAll() {
        List<SeriesEntity> list = seriesService.list(new QueryWrapper<SeriesEntity>());

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{seriesId}")
    @RequiresPermissions("business:series:info")
    public R info(@PathVariable("seriesId") Long seriesId) {
        SeriesEntity series = seriesService.getById(seriesId);

        return R.ok().put("series", series);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:series:save")
    public R save(@RequestBody SeriesEntity series) {

        series.setSeriesFctMaxPrice(BigDecimal.ZERO);
        series.setSeriesFctMinPrice(BigDecimal.ZERO);
        series.setCreateUser(this.getUserId());
        series.setCreateTime(new Date());

        if (seriesService.insertSeries(series)) {
            return R.ok();
        }

        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:series:update")
    public R update(@RequestBody SeriesEntity series) {

        series.setModifyUser(this.getUserId());
        series.setModifyTime(new Date());
        if (seriesService.updateSeriesById(series)) {
            return R.ok();
        }

        return R.error();

    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:series:delete")
    public R delete(@RequestBody Long[] seriesIds) {
        if (seriesService.removeSeriesByIds(Arrays.asList(seriesIds))) {
            return R.ok();
        }
        return R.error();
    }
}
