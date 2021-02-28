package io.renren.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 维修项目表
 * 
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-02-26 15:59:13
 */
@Data
@TableName("ma_items")
public class MaItemsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 维修项目id
	 */
	@TableId
	private Long itemId;
	/**
	 * 维修项目
	 */
	private String item;
	/**
	 * 维修项目单价
	 */
	private BigDecimal unitPrice;
	/**
	 * 删除状态:0.未删除，1.已删除
	 */
	@TableLogic
	private Integer delState;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Integer createUser;
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	/**
	 * 修改人
	 */
	private Integer modifyUser;

}
