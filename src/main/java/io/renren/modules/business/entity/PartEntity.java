package io.renren.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 配件表
 * 
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:31
 */
@Data
@TableName("part")
public class PartEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 配件id
	 */
	@TableId
	private Long partId;
	/**
	 * 配件名
	 */
	private String name;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 库存数量
	 */
	private BigDecimal amount;
	/**
	 * 参考单价
	 */
	private BigDecimal price;
	/**
	 * 备注
	 */
	private String comment;
	/**
	 * 删除状态:0.未删除，1.已删除
	 */
	private Integer delState;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long createUser;
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	/**
	 * 修改人
	 */
	private Long modifyUser;

}
