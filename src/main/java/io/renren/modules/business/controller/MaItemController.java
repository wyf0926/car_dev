package io.renren.modules.business.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.modules.business.entity.PartEntity;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.business.entity.MaItemEntity;
import io.renren.modules.business.service.MaItemService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 维修项目表
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-12 10:08:37
 */
@RestController
@RequestMapping("business/maitem")
public class MaItemController extends AbstractController {
    @Autowired
    private MaItemService maItemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("business:maitem:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = maItemService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @GetMapping("/listAll")
    @RequiresPermissions("business:part:list")
    public R listAll() {
        List<MaItemEntity> list = maItemService.list(new QueryWrapper<>());
        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{maItemId}")
    @RequiresPermissions("business:maitem:info")
    public R info(@PathVariable("maItemId") Integer maItemId){
		MaItemEntity maItem = maItemService.getById(maItemId);

        return R.ok().put("maItem", maItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("business:maitem:save")
    public R save(@RequestBody MaItemEntity maItem){
        maItem.setCreateUser(this.getUserId());
        maItem.setCreateTime(new Date());
		if (maItemService.saveMaItem(maItem)) {
            return R.ok();
        }

		return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("business:maitem:update")
    public R update(@RequestBody MaItemEntity maItem){
        maItem.setModifyUser(this.getUserId());
        maItem.setModifyTime(new Date());
		if (maItemService.updateMaItemById(maItem)) {
            return R.ok();
        }

		return R.error();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("business:maitem:delete")
    public R delete(@RequestBody Integer[] maItemIds){
		if (maItemService.removeByIds(Arrays.asList(maItemIds))) {
            return R.ok();
        }

		return R.error();

    }

}
