package io.renren.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 维修项目表
 * 
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-12 10:08:37
 */
@Data
@TableName("ma_item")
public class MaItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 维修项目id
	 */
	@TableId
	private Long maItemId;
	/**
	 * 维修项目名
	 */
	private String itemName;
	/**
	 * 参考单价
	 */
	private BigDecimal referPrice;
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
