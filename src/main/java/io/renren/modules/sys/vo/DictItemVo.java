package io.renren.modules.sys.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: allan
 * @Date: 2021/3/5 11:05 上午
 * @Email: zwy1997213@163.com
 * @Version: 1.0
 */
@Data
public class DictItemVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典项文本
     */
    private String itemText;

    /**
     * 字典项值
     */
    private String itemValue;
}
