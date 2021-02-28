package io.renren.modules.business.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.modules.business.vo.OrdersVo;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.business.entity.OrdersEntity;
import io.renren.modules.business.service.OrderService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 维修单表

 *
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-02-28 13:30:42
 */
@RestController
@RequestMapping("business/order")
@Api("维修单相关接口")
public class OrderController extends AbstractController {
    @Autowired
    private OrderService orderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:order:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderId}")
    @RequiresPermissions("business:order:info")
    public R info(@PathVariable("orderId") Long orderId){
		OrdersVo order = orderService.getOrderDetailById(orderId);

        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:order:save")
    @ApiOperation("新增维修单接口测试")
    public R save(@RequestBody OrdersVo order){
        order.setCreateTime(new Date());
        order.setCreateUser(this.getUserId());
		orderService.saveOrder(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:order:update")
    public R update(@RequestBody OrdersEntity order){
		orderService.updateById(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:order:delete")
    public R delete(@RequestBody Integer[] orderIds){
		orderService.removeByIds(Arrays.asList(orderIds));

        return R.ok();
    }

}
