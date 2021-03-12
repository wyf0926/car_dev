package io.renren.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单配件关系表（详情表）
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:31
 */
@Data
@TableName("order_part_rel")
public class OrderPartRelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 关联id
     */
    @TableId
    private Long relId;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 配件名
     */
    private String partName;
    /**
     * 配件单位
     */
    private String partUnit;
    /**
     * 实际单价
     */
    private BigDecimal realPrice;
    /**
     * 使用数量
     */
    private BigDecimal usedQuantity;
    /**
     * 配件总价
     */
    private BigDecimal totalAmount;
    /**
     * 备注
     */
    private String comment;

}
