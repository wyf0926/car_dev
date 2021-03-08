package io.renren.modules.business.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.business.entity.OrderMaItemsRelEntity;
import io.renren.modules.business.service.OrderMaItemsRelService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 维修单维修项目关系表（详情表）
 *
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-02-26 15:59:13
 */
@RestController
@RequestMapping("business/ordermaitemsrel")
public class OrderMaItemsRelController {
    @Autowired
    private OrderMaItemsRelService orderMaItemsRelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:ordermaitemsrel:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderMaItemsRelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{relId}")
    @RequiresPermissions("business:ordermaitemsrel:info")
    public R info(@PathVariable("relId") Integer relId) {
        OrderMaItemsRelEntity orderMaItemsRel = orderMaItemsRelService.getById(relId);

        return R.ok().put("orderMaItemsRel", orderMaItemsRel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:ordermaitemsrel:save")
    public R save(@RequestBody OrderMaItemsRelEntity orderMaItemsRel) {
        orderMaItemsRelService.save(orderMaItemsRel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:ordermaitemsrel:update")
    public R update(@RequestBody OrderMaItemsRelEntity orderMaItemsRel) {
        orderMaItemsRelService.updateById(orderMaItemsRel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:ordermaitemsrel:delete")
    public R delete(@RequestBody Integer[] relIds) {
        orderMaItemsRelService.removeByIds(Arrays.asList(relIds));

        return R.ok();
    }

}
