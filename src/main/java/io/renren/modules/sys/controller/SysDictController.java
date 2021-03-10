package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.service.SysDictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


/**
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-24 15:03:25
 */
@RestController
@RequestMapping("sys/sysdict")
public class SysDictController extends AbstractController {
    @Autowired
    private SysDictService sysDictService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:sysdict:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysDictService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("business:sysdict:info")
    public R info(@PathVariable("id") String id) {
        SysDictEntity sysDict = sysDictService.getById(id);

        return R.ok().put("sysDict", sysDict);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:sysdict:save")
    public R save(@RequestBody SysDictEntity sysDict) {

        sysDict.setCreateBy(this.getUserId());
        sysDict.setCreateTime(new Date());
        if (sysDictService.saveSysDictItem(sysDict)) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:sysdict:update")
    public R update(@RequestBody SysDictEntity sysDict) {
        sysDict.setUpdateBy(this.getUserId());
        sysDict.setUpdateTime(new Date());
        if (sysDictService.updateDictById(sysDict)) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:sysdict:delete")
    public R delete(@RequestBody Long[] ids) {

        if (sysDictService.removeSysDictByIds(Arrays.asList(ids))) {
            return R.ok();
        }
        return R.error();
    }

}
