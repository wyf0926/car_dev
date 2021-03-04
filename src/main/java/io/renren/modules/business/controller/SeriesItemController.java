package io.renren.modules.business.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.business.entity.SeriesItemEntity;
import io.renren.modules.business.service.SeriesItemService;
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
@RequestMapping("business/seriesitem")
public class SeriesItemController {
    @Autowired
    private SeriesItemService seriesItemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seriesItemService.queryPage(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/{seriesId}")
    public R getItemList(@PathVariable("seriesId") Long seriesId){
        List<SeriesItemEntity> list = seriesItemService.list(new QueryWrapper<SeriesItemEntity>().lambda().eq(SeriesItemEntity::getSeriesId,seriesId));

        return R.ok().put("itemList",list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemId}")
    public R info(@PathVariable("itemId") Long itemId){
		SeriesItemEntity seriesItem = seriesItemService.getById(itemId);

        return R.ok().put("seriesItem", seriesItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SeriesItemEntity seriesItem){
		seriesItemService.save(seriesItem);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SeriesItemEntity seriesItem){
		seriesItemService.updateById(seriesItem);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] itemIds){
		seriesItemService.removeByIds(Arrays.asList(itemIds));

        return R.ok();
    }

}
