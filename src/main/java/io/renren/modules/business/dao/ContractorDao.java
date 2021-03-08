package io.renren.modules.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.business.entity.ContractorEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 承修方表
 *
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-02-26 15:59:13
 */
@Mapper
public interface ContractorDao extends BaseMapper<ContractorEntity> {

}
