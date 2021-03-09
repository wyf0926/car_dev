package io.renren.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 承修方表
 *
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-02-26 15:59:13
 */
@Data
@TableName("contractor")
public class ContractorEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 承修方id
     */
    @TableId
    private Long contractorId;
    /**
     * 单位名称
     */
    private String contractorName;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 单位地址
     */
    private String address;
    /**
     * 开户银行
     */
    private String bank;
    /**
     * 银行账号
     */
    private String cardNo;
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
