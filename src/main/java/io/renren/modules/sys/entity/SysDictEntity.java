package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-24 15:03:25
 */
@Data
@TableName("sys_dict")
public class SysDictEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     * 字典名称
     */
    private String dictName;
    /**
     * 字典编码
     */
    private String dictCode;
    /**
     * 描述
     */
    private String description;
    /**
     * 删除状态
     */
    @TableLogic
    private Integer delFlag;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private Long updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 字典类型0为string,1为number
     */
    private Integer type;

}
