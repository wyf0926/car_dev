package io.renren.modules.business.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.modules.business.entity.SeriesEntity;
import io.renren.modules.business.entity.SeriesItemEntity;
import io.renren.modules.business.service.SeriesService;
import io.renren.modules.business.vo.CustomerCarVo;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.business.entity.CustomerCarSeriesRelEntity;
import io.renren.modules.business.service.CustomerCarSeriesRelService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.annotation.Resource;


/**
 * 
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-09 11:29:48
 */
@RestController
@RequestMapping("business/customercarseriesrel")
public class CustomerCarSeriesRelController extends AbstractController {
    @Resource
    private CustomerCarSeriesRelService customerCarSeriesRelService;

    @Resource
    private SeriesService seriesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:customer:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = customerCarSeriesRelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 根据客户id获取车辆列表
     */
    @GetMapping("/{customerId}")
    @RequiresPermissions("business:customer:list")
    public R listCar(@PathVariable("customerId") Long customerId){
        List<CustomerCarVo> itemList = customerCarSeriesRelService.getCarListByCustomerId(customerId);
        return R.ok().put("itemList", itemList);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{relId}")
    @RequiresPermissions("business:customer:info")
    public R info(@PathVariable("relId") Long relId){

		CustomerCarSeriesRelEntity customerCarSeriesRel = customerCarSeriesRelService.getById(relId);
        SeriesEntity series = seriesService.getById(customerCarSeriesRel.getSeriesId());

        CustomerCarVo customerCarVo = new CustomerCarVo();
        BeanUtil.copyProperties(customerCarSeriesRel,customerCarVo);

        customerCarVo.setSeriesName(series.getSeriesName());

        return R.ok().put("customerCarVo", customerCarVo);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:customer:save")
    public R save(@RequestBody CustomerCarVo customerCarVo){
        customerCarVo.setCreateTime(new Date());
        customerCarVo.setCreateUser(this.getUserId());

		if (customerCarSeriesRelService.saveCustomerCarSeriesRel(customerCarVo)) {
            return R.ok();
        }

		return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:customer:update")
    public R update(@RequestBody CustomerCarSeriesRelEntity customerCarSeriesRel){
        customerCarSeriesRel.setModifyTime(new Date());
        customerCarSeriesRel.setModifyUser(this.getUserId());

		if (customerCarSeriesRelService.updateCustomerCarSeriesRelById(customerCarSeriesRel)) {
            return R.ok();
        }

		return R.error();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:customer:delete")
    public R delete(@RequestBody Long[] relIds){
		if (customerCarSeriesRelService.removeByCustomerCarSeriesRelIds(Arrays.asList(relIds))) {
            return R.ok();
        }

		return R.error();
    }

}
