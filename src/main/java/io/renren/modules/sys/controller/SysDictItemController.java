package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysDictItemEntity;
import io.renren.modules.sys.service.SysDictItemService;
import io.renren.modules.sys.vo.DictItemVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-24 15:03:25
 */
@RestController
@RequestMapping("sys/sysdictitem")
public class SysDictItemController extends AbstractController {

    @Autowired
    private SysDictItemService sysDictItemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:sysdictitem:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysDictItemService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 根据字典id获取字典项分页列表
     */
    @GetMapping
    @RequiresPermissions("business:sysdict:list")
    public R listItem(@RequestParam Map<String, Object> params) {
        PageUtils page = sysDictItemService.queryItemPage(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/{dictId}")
    @RequiresPermissions("business:sysdict:list")
    public R listItem(@PathVariable("dictId") Long dictId) {
        List<DictItemVo> dictList = sysDictItemService.queryItemList(dictId);

        return R.ok().put("dictList", dictList);
    }

    @GetMapping("/itemlist")
    @RequiresPermissions("business:sysdict:list")
    public R listItem(@RequestParam("dictCode") String dictCode) {
        List<DictItemVo> dictList = sysDictItemService.queryItemListByCode(dictCode);

        return R.ok().put("dictList", dictList);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("business:sysdict:info")
    public R info(@PathVariable("id") String id) {
        SysDictItemEntity sysDictItem = sysDictItemService.getById(id);

        return R.ok().put("sysDictItem", sysDictItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:sysdict:save")
    public R save(@RequestBody SysDictItemEntity sysDictItem) {
        sysDictItem.setCreateBy(this.getUserId());
        sysDictItem.setCreateTime(new Date());
        if (sysDictItemService.saveDictItem(sysDictItem)) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:sysdict:update")
    public R update(@RequestBody SysDictItemEntity sysDictItem) {
        sysDictItem.setUpdateBy(this.getUserId());
        sysDictItem.setUpdateTime(new Date());
        if (sysDictItemService.updateDictItemById(sysDictItem)) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:sysdict:delete")
    public R delete(@RequestBody String[] ids) {

        if (sysDictItemService.removeByIds(Arrays.asList(ids))) {
            return R.ok();
        }
        return R.error();
    }

}
