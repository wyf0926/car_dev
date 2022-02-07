package io.renren.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 维修单表

 * 
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-03-09 10:11:29
 */
@Data
@TableName("orders")
public class OrdersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 维修单id
	 */
	@TableId
	private Long orderId;
	/**
	 * 维修编号
	 */
	private String orderNo;
	/**
	 * 客户汽车关系id
	 */
	private Long relId;
	/**
	 * 车牌号
	 */
	private String carPlate;
	/**
	 * 车架号
	 */
	private String vin;
	/**
	 * 发动机号
	 */
	private String engineNo;
	/**
	 * 客户id
	 */
	private Integer customerId;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 送修人
	 */
	private String driver;
	/**
	 * 维修类别
	 */
	private String category;
	/**
	 * 进厂日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date incomingDate;
	/**
	 * 出厂里程表读数
	 */
	private Long mileageAfter;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 承修方id
	 */
	private Long contractorId;
	/**
	 * 结算日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date payDate;
	/**
	 * 支付方式:0.现金，1.银行，2.移动支付
	 */
	private Integer payType;
	/**
	 * 总金额
	 */
	private BigDecimal totalAmount;
	/**
	 * 旧配件处理方式
	 */
	private Integer oldPart;
	/**
	 * 备注
	 */
	private String comment;
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
