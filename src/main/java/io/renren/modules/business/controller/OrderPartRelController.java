package io.renren.modules.business.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.business.entity.OrderPartRelEntity;
import io.renren.modules.business.service.OrderPartRelService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 订单配件关系表（详情表）
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:31
 */
@RestController
@RequestMapping("business/orderpartrel")
public class OrderPartRelController extends AbstractController {
    @Autowired
    private OrderPartRelService orderPartRelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:orderpartrel:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderPartRelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{relId}")
    @RequiresPermissions("business:orderpartrel:info")
    public R info(@PathVariable("relId") Integer relId) {
        OrderPartRelEntity orderPartRel = orderPartRelService.getById(relId);

        return R.ok().put("orderPartRel", orderPartRel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:orderpartrel:save")
    public R save(@RequestBody OrderPartRelEntity orderPartRel) {
        orderPartRelService.save(orderPartRel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:orderpartrel:update")
    public R update(@RequestBody OrderPartRelEntity orderPartRel) {
        orderPartRelService.updateById(orderPartRel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:orderpartrel:delete")
    public R delete(@RequestBody Integer[] relIds) {
        if (orderPartRelService.removeByIds(Arrays.asList(relIds))) {
            return R.ok();
        }
        return R.error();
    }

}
