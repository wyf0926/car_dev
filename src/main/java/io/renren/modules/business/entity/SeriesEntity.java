package io.renren.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-04 16:31:29
 */
@Data
@TableName("car_series")
public class SeriesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 车系id
	 */
	@TableId
	private Long seriesId;
	/**
	 *  车系名
	 */
	private String seriesName;
	/**
	 *  车系级别
	 */
	private Integer seriesRank;
	/**
	 * 车型
	 */
	private String levelName;
	/**
	 * 车型id
	 */
	private Integer levelId;
	/**
	 * 车系最高价格
	 */
	private BigDecimal seriesFctMaxPrice;
	/**
	 * 车系最低价格
	 */
	private BigDecimal seriesFctMinPrice;
	/**
	 * 车系图片地址
	 */
	private String seriesImg;
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
