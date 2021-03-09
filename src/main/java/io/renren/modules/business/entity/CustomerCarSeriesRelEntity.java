package io.renren.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-03-09 10:11:29
 */
@Data
@TableName("customer_car_series_rel")
public class CustomerCarSeriesRelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 关联id
	 */
	@TableId
	private Integer relId;
	/**
	 * 客户id
	 */
	private Integer customerId;
	/**
	 * 车系id
	 */
	private Integer seriesId;
	/**
	 * 车牌号码
	 */
	private String carPlate;
	/**
	 * 车架号
	 */
	private String vin;

}
