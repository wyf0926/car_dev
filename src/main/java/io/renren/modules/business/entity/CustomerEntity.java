package io.renren.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 客户表

 * 
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:30
 */
@Data
@TableName("customer")
public class CustomerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 客户id
	 */
	@TableId
	private Integer customerId;
	/**
	 * 客户姓名
	 */
	private String name;
	/**
	 * 客户类型:1.个人，2.公司
	 */
	private Integer type;
	/**
	 * 联系方式
	 */
	private String phone;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 银行卡号
	 */
	private String cardNo;
	/**
	 * 银行卡开户行
	 */
	private String bank;
	/**
	 * 历史消费金额
	 */
	private BigDecimal total;
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
