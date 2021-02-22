package io.renren.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 维修单表

 * 
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:31
 */
@Data
@TableName("order")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 维修单id
	 */
	@TableId
	private Long orderId;
	/**
	 * 维修单号
	 */
	private Long orderNo;
	/**
	 * 客户编号
	 */
	private Long customerId;
	/**
	 * 结算日期
	 */
	private Date orderTime;
	/**
	 * 车牌号码
	 */
	private String carPlate;
	/**
	 * 发动机号
	 */
	private String engine;
	/**
	 * 支付方式:0.现金，1.银行，2.移动支付
	 */
	private Integer payType;
	/**
	 * 总金额
	 */
	private BigDecimal totalAmount;
	/**
	 * 备注
	 */
	private String comment;
	/**
	 * 工时费
	 */
	private BigDecimal fee;
	/**
	 * 维修人
	 */
	private String worker;
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
