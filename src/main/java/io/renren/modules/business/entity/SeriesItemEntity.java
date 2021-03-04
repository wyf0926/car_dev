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
@TableName("series_item")
public class SeriesItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 车款id
	 */
	@TableId
	private Long itemId;
	/**
	 * 车系id
	 */
	private Long seriesId;
	/**
	 * 车款名
	 */
	private String specName;
	/**
	 * 参考价格
	 */
	private BigDecimal referPrice;
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
