/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.modules.business.dao.SeriesDao;
import io.renren.modules.business.dao.SeriesItemDao;
import io.renren.modules.business.entity.SeriesItemEntity;
import io.renren.modules.business.service.SeriesItemService;
import io.renren.service.DynamicDataSourceTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 多数据源测试
 *
 * @author Mark sunlightcs@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDataSourceTest {
    @Autowired
    private DynamicDataSourceTestService dynamicDataSourceTestService;
    @Resource
    private SeriesItemDao seriesItemDao;

    @Test
    public void test(){
        Long id = 1L;

        dynamicDataSourceTestService.updateUser(id);
        dynamicDataSourceTestService.updateUserBySlave1(id);
        dynamicDataSourceTestService.updateUserBySlave2(id);
    }

    @Test
    public void testMap(){
        List<BigDecimal> collect = seriesItemDao.selectObjs(
                new LambdaQueryWrapper<SeriesItemEntity>()
                        .select(SeriesItemEntity::getReferPrice)
                        .eq(SeriesItemEntity::getSeriesId, 6037))
                .stream()
                .map(o -> (BigDecimal) o)
                .collect(Collectors.toList());

        System.err.println(collect.size());

    }

}
