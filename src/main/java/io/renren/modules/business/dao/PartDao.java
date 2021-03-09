package io.renren.modules.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.business.entity.PartEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 配件表
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:31
 */
@Mapper
public interface PartDao extends BaseMapper<PartEntity> {

}
