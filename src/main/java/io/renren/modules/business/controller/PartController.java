package io.renren.modules.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.business.entity.PartEntity;
import io.renren.modules.business.entity.SeriesEntity;
import io.renren.modules.business.service.PartService;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 配件表
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:31
 */
@RestController
@RequestMapping("business/part")
public class PartController extends AbstractController {
    @Autowired
    private PartService partService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:part:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = partService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @GetMapping("/listAll")
    @RequiresPermissions("business:part:list")
    public R listAll() {
        List<PartEntity> list = partService.list(new QueryWrapper<PartEntity>());

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{partId}")
    @RequiresPermissions("business:part:info")
    public R info(@PathVariable("partId") Integer partId) {
        PartEntity part = partService.getById(partId);

        return R.ok().put("part", part);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:part:save")
    public R save(@RequestBody PartEntity part) {
        part.setCreateUser(this.getUserId());
        part.setCreateTime(new Date());

        if (partService.savePart(part)) {
            return R.ok();
        }

        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:part:update")
    public R update(@RequestBody PartEntity part) {
        part.setModifyUser(this.getUserId());
        part.setModifyTime(new Date());

        if (partService.updatePartById(part)) {
            return R.ok();
        }

        return R.error();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:part:delete")
    public R delete(@RequestBody Integer[] partIds) {
        if (partService.removeByIds(Arrays.asList(partIds))) {
            return R.ok();
        }
        return R.error();
    }

}
