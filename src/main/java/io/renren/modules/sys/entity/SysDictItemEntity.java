package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-24 15:03:25
 */
@Data
@TableName("sys_dict_item")
public class SysDictItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 字典id
	 */
	private Long dictId;
	/**
	 * 字典项文本
	 */
	private String itemText;
	/**
	 * 字典项值
	 */
	private String itemValue;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 排序
	 */
	private Integer sortOrder;
	/**
	 * 状态（1启用 0不启用）
	 */
	private Integer status;
	/**
	 * 
	 */
	private Long createBy;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Long updateBy;
	/**
	 * 
	 */
	private Date updateTime;

}
