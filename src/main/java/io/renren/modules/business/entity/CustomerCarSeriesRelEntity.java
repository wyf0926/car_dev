package io.renren.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-09 11:29:48
 */
@Data
@TableName("customer_car_series_rel")
public class CustomerCarSeriesRelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 关联id
	 */
	@TableId
	private Long relId;
	/**
	 * 客户id
	 */
	private Long customerId;
	/**
	 * 车系id
	 */
	private Long seriesId;
	/**
	 * 车牌号码
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
