package io.renren.modules.business.dao;

import io.renren.modules.business.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 维修单表

 * 
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:31
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
