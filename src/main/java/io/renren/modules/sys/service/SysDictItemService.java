package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysDictItemEntity;
import io.renren.modules.sys.vo.DictItemVo;

import java.util.List;
import java.util.Map;

/**
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-24 15:03:25
 */
public interface SysDictItemService extends IService<SysDictItemEntity> {

    /**
     * 字典项分页列表(自动生成)
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 字典项分页列表
     *
     * @param params
     * @return
     */
    PageUtils queryItemPage(Map<String, Object> params);

    /**
     * 字典项列表
     *
     * @param dictId
     * @return
     */
    List<DictItemVo> queryItemList(Long dictId);

    /**
     * 字典项保存
     *
     * @param sysDictItem
     * @return
     */
    boolean saveDictItem(SysDictItemEntity sysDictItem);

    /**
     * 字典项更新
     *
     * @param sysDictItem
     * @return
     */
    boolean updateDictItemById(SysDictItemEntity sysDictItem);

    /**
     * 字典项查询
     *
     * @param dictCode
     * @return
     */
    List<DictItemVo> queryItemListByCode(String dictCode);
}

