package io.renren.modules.business.dao;

import io.renren.modules.business.entity.CustomerCarSeriesRelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.business.vo.CustomerCarVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-09 11:29:48
 */
@Mapper
public interface CustomerCarSeriesRelDao extends BaseMapper<CustomerCarSeriesRelEntity> {

    List<CustomerCarVo> selectCarListByCustomerId(@Param("customerId") Long customerId);

}
