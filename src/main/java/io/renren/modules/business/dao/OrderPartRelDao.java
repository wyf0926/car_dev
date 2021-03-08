package io.renren.modules.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.business.entity.OrderPartRelEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单配件关系表（详情表）
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:31
 */
@Mapper
public interface OrderPartRelDao extends BaseMapper<OrderPartRelEntity> {

}
