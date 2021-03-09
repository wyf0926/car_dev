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

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryItemPage(Map<String, Object> params);

    List<DictItemVo> queryItemList(Long dictId);

    boolean saveDictItem(SysDictItemEntity sysDictItem);

    boolean updateDictItemById(SysDictItemEntity sysDictItem);

    List<DictItemVo> queryItemListByCode(String dictCode);
}

