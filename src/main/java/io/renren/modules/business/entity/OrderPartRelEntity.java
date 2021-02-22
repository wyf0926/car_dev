package io.renren.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单配件关系表（详情表）
 * 
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:31
 */
@Data
@TableName("order_part_rel")
public class OrderPartRelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 关联id
	 */
	@TableId
	private Long relId;
	/**
	 * 订单id
	 */
	private Long orderId;
	/**
	 * 配件id
	 */
	private Long partId;
	/**
	 * 实际单价
	 */
	private BigDecimal realPrice;
	/**
	 * 使用数量
	 */
	private Integer usedQuantity;
	/**
	 * 配件总价
	 */
	private BigDecimal totalAmount;

}
