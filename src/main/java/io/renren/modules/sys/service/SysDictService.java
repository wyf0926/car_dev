package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysDictEntity;

import java.util.List;
import java.util.Map;

/**
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-24 15:03:25
 */
public interface SysDictService extends IService<SysDictEntity> {

    /**
     * 字典分页列表
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增字典
     *
     * @param sysDict
     * @return
     */
    boolean saveSysDictItem(SysDictEntity sysDict);

    /**
     * 更新字典
     *
     * @param sysDict
     * @return
     */
    boolean updateDictById(SysDictEntity sysDict);

    /**
     * 删除字典
     *
     * @param list
     * @return
     */
    boolean removeSysDictByIds(List<Long> list);
}

