package io.renren.modules.business.vo;

import io.renren.modules.business.entity.OrdersEntity;
import io.renren.modules.business.entity.OrderMaItemsRelEntity;
import io.renren.modules.business.entity.PartEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author WYF
 * @Date 2021/2/28 2:07 下午
 * @Email wyf0926@seas.upenn.edu
 * @Version 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class OrdersVo extends OrdersEntity {

    private List<PartVo> partList;

    private List<OrderMaItemsRelEntity> orderMaItemsRelEntities;

}
