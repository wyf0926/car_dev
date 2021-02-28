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

import io.renren.modules.business.entity.ContractorEntity;
import io.renren.modules.business.service.ContractorService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 承修方表
 *
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-02-26 15:59:13
 */
@RestController
@RequestMapping("business/contractor")
public class ContractorController {
    @Autowired
    private ContractorService contractorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:contractor:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = contractorService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{contractorId}")
    @RequiresPermissions("business:contractor:info")
    public R info(@PathVariable("contractorId") Integer contractorId){
		ContractorEntity contractor = contractorService.getById(contractorId);

        return R.ok().put("contractor", contractor);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:contractor:save")
    public R save(@RequestBody ContractorEntity contractor){
		contractorService.save(contractor);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:contractor:update")
    public R update(@RequestBody ContractorEntity contractor){
		contractorService.updateById(contractor);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:contractor:delete")
    public R delete(@RequestBody Integer[] contractorIds){
		contractorService.removeByIds(Arrays.asList(contractorIds));

        return R.ok();
    }

}
