package io.renren.modules.business.vo;

import io.renren.modules.business.entity.PartEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @Author WYF
 * @Date 2021/3/2 8:58 下午
 * @Email wyf0926@seas.upenn.edu
 * @Version 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class PartVo extends PartEntity {
    private BigDecimal usedQuantity;
    private BigDecimal totalAmount;
}
