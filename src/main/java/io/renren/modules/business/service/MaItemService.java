package io.renren.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.business.entity.MaItemEntity;

import java.util.Map;

/**
 * 维修项目表
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-12 10:08:37
 */
public interface MaItemService extends IService<MaItemEntity> {

    /**
     * 分页列表查询
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 维修项目新增
     *
     * @param maItem
     * @return
     */
    boolean saveMaItem(MaItemEntity maItem);

    /**
     * 维修项目更新
     *
     * @param maItem
     * @return
     */
    boolean updateMaItemById(MaItemEntity maItem);
}

