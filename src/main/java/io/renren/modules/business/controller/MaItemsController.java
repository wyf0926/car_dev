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

import io.renren.modules.business.entity.MaItemsEntity;
import io.renren.modules.business.service.MaItemsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 维修项目表
 *
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-02-26 15:59:13
 */
@RestController
@RequestMapping("business/maitems")
public class MaItemsController {
    @Autowired
    private MaItemsService maItemsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:maitems:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = maItemsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemId}")
    @RequiresPermissions("business:maitems:info")
    public R info(@PathVariable("itemId") Integer itemId){
		MaItemsEntity maItems = maItemsService.getById(itemId);

        return R.ok().put("maItems", maItems);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:maitems:save")
    public R save(@RequestBody MaItemsEntity maItems){
		maItemsService.save(maItems);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:maitems:update")
    public R update(@RequestBody MaItemsEntity maItems){
		maItemsService.updateById(maItems);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:maitems:delete")
    public R delete(@RequestBody Integer[] itemIds){
		maItemsService.removeByIds(Arrays.asList(itemIds));

        return R.ok();
    }

}
