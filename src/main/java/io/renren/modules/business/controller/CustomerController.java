package io.renren.modules.business.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.business.entity.CustomerEntity;
import io.renren.modules.business.service.CustomerService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 客户表

 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:30
 */
@RestController
@RequestMapping("business/customer")
@Api("客户管理接口")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:customer:list")
    @ApiOperation("客户列表接口")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = customerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{customerId}")
    @RequiresPermissions("business:customer:info")
    public R info(@PathVariable("customerId") Integer customerId){
		CustomerEntity customer = customerService.getById(customerId);

        return R.ok().put("customer", customer);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:customer:save")
    public R save(@RequestBody CustomerEntity customer){
		customerService.save(customer);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:customer:update")
    public R update(@RequestBody CustomerEntity customer){
		customerService.updateById(customer);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:customer:delete")
    public R delete(@RequestBody Integer[] customerIds){
		customerService.removeByIds(Arrays.asList(customerIds));

        return R.ok();
    }

}
