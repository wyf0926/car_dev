package io.renren.modules.business.dao;

import io.renren.modules.business.entity.MaItemsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 维修项目表
 * 
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-02-26 15:59:13
 */
@Mapper
public interface MaItemsDao extends BaseMapper<MaItemsEntity> {
	
}
