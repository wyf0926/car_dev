package io.renren.modules.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.business.entity.CustomerEntity;
import io.renren.modules.business.entity.SeriesEntity;
import io.renren.modules.business.service.CustomerService;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


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
public class CustomerController extends AbstractController {
    @Autowired
    private CustomerService customerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:customer:list")
    @ApiOperation("客户列表接口")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = customerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{customerId}")
    @RequiresPermissions("business:customer:info")
    public R info(@PathVariable("customerId") Integer customerId) {
        CustomerEntity customer = customerService.getById(customerId);

        return R.ok().put("customer", customer);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:customer:save")
    public R save(@RequestBody CustomerEntity customer) {

        customer.setCreateUser(this.getUserId());
        customer.setCreateTime(new Date());

        if (customerService.saveCustomer(customer)) {
            return R.ok();

        }

        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:customer:update")
    public R update(@RequestBody CustomerEntity customer) {


        customer.setModifyUser(this.getUserId());
        customer.setModifyTime(new Date());

        if (customerService.updateCustomerById(customer)) {
            return R.ok();
        }

        return R.error();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:customer:delete")
    public R delete(@RequestBody Integer[] customerIds) {

        if (customerService.removeByIds(Arrays.asList(customerIds))) {
            return R.ok();
        }

        return R.error();
    }

}
