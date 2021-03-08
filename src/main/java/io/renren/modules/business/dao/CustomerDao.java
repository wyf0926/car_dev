package io.renren.modules.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.business.entity.CustomerEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户表
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:30
 */
@Mapper
public interface CustomerDao extends BaseMapper<CustomerEntity> {

}
