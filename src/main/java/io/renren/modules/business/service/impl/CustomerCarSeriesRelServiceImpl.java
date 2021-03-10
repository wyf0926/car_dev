package io.renren.modules.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.renren.common.exception.RRException;
import io.renren.modules.business.vo.CustomerCarVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
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


@Service("customerCarSeriesRelService")
public class CustomerCarSeriesRelServiceImpl extends ServiceImpl<CustomerCarSeriesRelDao, CustomerCarSeriesRelEntity> implements CustomerCarSeriesRelService {

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
    public boolean saveCustomerCarSeriesRel(CustomerCarSeriesRelEntity customerCarSeriesRel) {
        // 将车牌所有字母转为大写
        customerCarSeriesRel.setCarPlate(customerCarSeriesRel.getCarPlate().toUpperCase());
        List<CustomerCarSeriesRelEntity> carPlateCheck = this.baseMapper.selectList(new LambdaQueryWrapper<CustomerCarSeriesRelEntity>()
                .eq(CustomerCarSeriesRelEntity::getCustomerId, customerCarSeriesRel.getCustomerId())
                .eq(CustomerCarSeriesRelEntity::getCarPlate, customerCarSeriesRel.getCarPlate()));
        List<CustomerCarSeriesRelEntity> vinCheck = this.baseMapper.selectList(new LambdaQueryWrapper<CustomerCarSeriesRelEntity>()
                .eq(CustomerCarSeriesRelEntity::getCustomerId, customerCarSeriesRel.getCustomerId())
                .eq(CustomerCarSeriesRelEntity::getVin, customerCarSeriesRel.getVin()));
        if (!carPlateCheck.isEmpty() || !vinCheck.isEmpty()) {
            throw new RRException("该客户名下已存在该车辆！", 501);
        }
        if (1 != this.baseMapper.insert(customerCarSeriesRel)) {
            throw new RRException("错误:新增时发生未知异常,请联系系统管理员!", 501);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCustomerCarSeriesRelById(CustomerCarSeriesRelEntity customerCarSeriesRel) {
        List<CustomerCarSeriesRelEntity> carPlateCheck = this.baseMapper.selectList(new LambdaQueryWrapper<CustomerCarSeriesRelEntity>()
                .eq(CustomerCarSeriesRelEntity::getCustomerId, customerCarSeriesRel.getCustomerId())
                .eq(CustomerCarSeriesRelEntity::getCarPlate, customerCarSeriesRel.getCarPlate())
                .ne(CustomerCarSeriesRelEntity::getRelId, customerCarSeriesRel.getRelId()));
        List<CustomerCarSeriesRelEntity> vinCheck = this.baseMapper.selectList(new LambdaQueryWrapper<CustomerCarSeriesRelEntity>()
                .eq(CustomerCarSeriesRelEntity::getCustomerId, customerCarSeriesRel.getCustomerId())
                .eq(CustomerCarSeriesRelEntity::getVin, customerCarSeriesRel.getVin())
                .ne(CustomerCarSeriesRelEntity::getRelId, customerCarSeriesRel.getRelId()));
        if (!carPlateCheck.isEmpty() || !vinCheck.isEmpty()) {
            throw new RRException("该客户名下已存在该车辆！", 501);
        }
        if (1 != this.baseMapper.updateById(customerCarSeriesRel)) {
            throw new RRException("错误:更新时发生未知异常,请联系系统管理员!", 501);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByCustomerCarSeriesRelIds(List<Long> relIdList) {
        int delCount = this.baseMapper.deleteBatchIds(relIdList);
        return delCount == relIdList.size();
    }



}