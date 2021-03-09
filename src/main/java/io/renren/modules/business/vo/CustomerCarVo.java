package io.renren.modules.business.vo;

import io.renren.modules.business.entity.CustomerCarSeriesRelEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: allan
 * @Date: 2021/3/9 2:47 下午
 * @Email: zwy1997213@163.com
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerCarVo extends CustomerCarSeriesRelEntity {

    private String seriesName;
}
