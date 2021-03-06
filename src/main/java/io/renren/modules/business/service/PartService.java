package io.renren.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.business.entity.PartEntity;

import java.util.Map;

/**
 * 配件表
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:31
 */
public interface PartService extends IService<PartEntity> {

    /**
     * 分页列表接口
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 配件新增
     *
     * @param part
     * @return
     */
    boolean savePart(PartEntity part);

    /**
     * 配件更新
     *
     * @param part
     * @return
     */
    boolean updatePartById(PartEntity part);
}

