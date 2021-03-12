package io.renren.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 维修单维修项目关系表（详情表）
 *
 * @author yifan
 * @email wyf0926@seas.upenn.edu
 * @date 2021-02-26 15:59:13
 */
@Data
@TableName("order_ma_items_rel")
public class OrderMaItemsRelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 关联id
     */
    @TableId
    private Long relId;
    /**
     * 维修单id
     */
    private Long orderId;
    /**
     * 维修项目
     */
    private String maItem;
    /**
     * 单价
     */
    private BigDecimal realPrice;
    /**
     * 维修工时
     */
    private BigDecimal manHour;
    /**
     * 总工时费
     */
    private BigDecimal totalAmount;
    /**
     * 备注
     */
    private String comment;

}
