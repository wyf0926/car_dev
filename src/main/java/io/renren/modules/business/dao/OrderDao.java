package io.renren.modules.business.dao;

import io.renren.modules.business.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 维修单表

 * 
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-02-28 13:30:42
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
