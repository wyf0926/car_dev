package io.renren.modules.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.business.entity.ContractorEntity;
import io.renren.modules.business.service.ContractorService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 承修方表
 *
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-02-26 15:59:13
 */
@RestController
@RequestMapping("business/contractor")
public class ContractorController extends AbstractController {
    @Autowired
    private ContractorService contractorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:contractor:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = contractorService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 获取全部承修方列表
     */
    @GetMapping("/listAll")
    @RequiresPermissions("business:contractor:list")
    public R listAll() {
        List<ContractorEntity> list = contractorService.list(new QueryWrapper<ContractorEntity>());

        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{contractorId}")
    @RequiresPermissions("business:contractor:info")
    public R info(@PathVariable("contractorId") Integer contractorId) {
        ContractorEntity contractor = contractorService.getById(contractorId);

        return R.ok().put("contractor", contractor);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:contractor:save")
    public R save(@RequestBody ContractorEntity contractor) {
        contractor.setCreateTime(new Date());
        contractor.setCreateUser(this.getUserId());
        if (contractorService.saveContractor(contractor)) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:contractor:update")
    public R update(@RequestBody ContractorEntity contractor) {
        contractor.setModifyTime(new Date());
        contractor.setModifyUser(this.getUserId());
        if (contractorService.updateContractorById(contractor)) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:contractor:delete")
    public R delete(@RequestBody Integer[] contractorIds) {
        if (contractorService.removeByIds(Arrays.asList(contractorIds))) {
            return R.ok();
        }
        return R.error();
    }

}
