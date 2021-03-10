package io.renren.modules.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.business.entity.SeriesItemEntity;
import io.renren.modules.business.service.SeriesItemService;
import io.renren.modules.sys.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-04 16:31:29
 */
@RestController
@RequestMapping("business/seriesitem")
public class SeriesItemController extends AbstractController {
    @Autowired
    private SeriesItemService seriesItemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = seriesItemService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 根据车系id获取全部车款列表
     *
     * @param seriesId
     * @return
     */
    @GetMapping("/{seriesId}")
    public R getItemList(@PathVariable("seriesId") Long seriesId) {
        List<SeriesItemEntity> list = seriesItemService.list(new QueryWrapper<SeriesItemEntity>().lambda().eq(SeriesItemEntity::getSeriesId, seriesId));

        return R.ok().put("itemList", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{itemId}")
    public R info(@PathVariable("itemId") Long itemId) {
        SeriesItemEntity seriesItem = seriesItemService.getById(itemId);

        return R.ok().put("seriesItem", seriesItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SeriesItemEntity seriesItem) {
        seriesItem.setCreateUser(this.getUserId());
        seriesItem.setCreateTime(new Date());

        if (seriesItemService.saveSeriesItem(seriesItem)) {
            return R.ok();
        }

        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SeriesItemEntity seriesItem) {

        seriesItem.setModifyUser(this.getUserId());
        seriesItem.setModifyTime(new Date());
        if (seriesItemService.updateSeriesItem(seriesItem)) {
            return R.ok();
        }

        return R.error();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] itemIds) {
        if (seriesItemService.removeByItemIds(Arrays.asList(itemIds))) {
            return R.ok();
        }

        return R.error();
    }

}
