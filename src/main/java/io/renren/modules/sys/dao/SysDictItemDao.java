package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.SysDictItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.vo.DictItemVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-24 15:03:25
 */
@Mapper
public interface SysDictItemDao extends BaseMapper<SysDictItemEntity> {

    List<DictItemVo> selectItemList(@Param("dictId") Long dictId);
}
