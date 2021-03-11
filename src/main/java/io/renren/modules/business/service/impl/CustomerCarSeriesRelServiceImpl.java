package io.renren.modules.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.renren.common.exception.RRException;
import io.renren.modules.business.entity.SeriesEntity;
import io.renren.modules.business.service.SeriesService;
import io.renren.modules.business.vo.CustomerCarVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.business.dao.CustomerCarSeriesRelDao;
import io.renren.modules.business.entity.CustomerCarSeriesRelEntity;
import io.renren.modules.business.service.CustomerCarSeriesRelService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-09 11:29:48
 */

@Service("customerCarSeriesRelService")
public class CustomerCarSeriesRelServiceImpl extends ServiceImpl<CustomerCarSeriesRelDao, CustomerCarSeriesRelEntity> implements CustomerCarSeriesRelService {

    @Resource
    private SeriesService seriesService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CustomerCarSeriesRelEntity> page = this.page(
                new Query<CustomerCarSeriesRelEntity>().getPage(params),
                new QueryWrapper<CustomerCarSeriesRelEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CustomerCarVo> getCarListByCustomerId(Long customerId) {
        return this.baseMapper.selectCarListByCustomerId(customerId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveCustomerCarSeriesRel(CustomerCarVo customerCarVo) {


        // 如果车系id为空则新增
        if (customerCarVo.getSeriesId() == null) {

            String seriesName = customerCarVo.getSeriesName();

            if ( seriesName == null || StringUtils.isBlank(seriesName)){
                throw new RRException("请输入车系名!",501);
            }

            SeriesEntity series = new SeriesEntity();
            series.setSeriesName(seriesName);
            series.setSeriesFctMaxPrice(BigDecimal.ZERO);
            series.setSeriesFctMinPrice(BigDecimal.ZERO);
            series.setCreateUser(customerCarVo.getCreateUser());
            series.setCreateTime(new Date());

            if (!seriesService.insertSeries(series)) {
                throw new RRException("错误:发生未知异常,请联系系统管理员!",501);
            }
            customerCarVo.setSeriesId(series.getSeriesId());
        }

        // 将车牌所有字母转为大写
        customerCarVo.setCarPlate(customerCarVo.getCarPlate().toUpperCase());
        List<CustomerCarSeriesRelEntity> list = this.baseMapper.selectList(new LambdaQueryWrapper<CustomerCarSeriesRelEntity>()
                .eq(CustomerCarSeriesRelEntity::getCustomerId, customerCarVo.getCustomerId())
                .and(wrapper -> wrapper
                        .eq(StringUtils.isNotBlank(customerCarVo.getCarPlate()), CustomerCarSeriesRelEntity::getCarPlate, customerCarVo.getCarPlate())
                        .or().eq(StringUtils.isNotBlank(customerCarVo.getVin()), CustomerCarSeriesRelEntity::getVin, customerCarVo.getVin())
                        .or().eq(StringUtils.isNotBlank(customerCarVo.getEngineNo()), CustomerCarSeriesRelEntity::getEngineNo, customerCarVo.getEngineNo()))
                );

        if (!list.isEmpty()) {
            throw new RRException("该客户名下已存在该车辆！", 501);
        }

        return this.baseMapper.insert(customerCarVo) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCustomerCarSeriesRelById(CustomerCarSeriesRelEntity customerCarSeriesRel) {
        List<CustomerCarSeriesRelEntity> list = this.baseMapper.selectList(new LambdaQueryWrapper<CustomerCarSeriesRelEntity>()
                .eq(CustomerCarSeriesRelEntity::getCustomerId, customerCarSeriesRel.getCustomerId())
                .ne(CustomerCarSeriesRelEntity::getRelId, customerCarSeriesRel.getRelId())
                .and(wrapper -> wrapper
                        .eq(StringUtils.isNotBlank(customerCarSeriesRel.getCarPlate()), CustomerCarSeriesRelEntity::getCarPlate, customerCarSeriesRel.getCarPlate())
                        .or().eq(StringUtils.isNotBlank(customerCarSeriesRel.getVin()), CustomerCarSeriesRelEntity::getVin, customerCarSeriesRel.getVin())
                        .or().eq(StringUtils.isNotBlank(customerCarSeriesRel.getEngineNo()), CustomerCarSeriesRelEntity::getEngineNo, customerCarSeriesRel.getEngineNo()))
        );

        if (!list.isEmpty()) {
            throw new RRException("该客户名下已存在该车辆！", 501);
        }

        return this.baseMapper.updateById(customerCarSeriesRel) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByCustomerCarSeriesRelIds(List<Long> relIdList) {
        int delCount = this.baseMapper.deleteBatchIds(relIdList);

        return delCount == relIdList.size();
    }



}