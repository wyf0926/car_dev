package io.renren.modules.business.service.impl;

import io.renren.common.exception.RRException;
import io.renren.modules.business.entity.PartEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.business.dao.MaItemDao;
import io.renren.modules.business.entity.MaItemEntity;
import io.renren.modules.business.service.MaItemService;
/**
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-03-12 10:08:37
 */

@Service("maItemService")
public class MaItemServiceImpl extends ServiceImpl<MaItemDao, MaItemEntity> implements MaItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<MaItemEntity> queryWrapper = new QueryWrapper<>();

        Object keyObj = params.get("key");

        if (keyObj != null) {
            if (StringUtils.isNotBlank(keyObj.toString())) {
                queryWrapper.lambda().like(MaItemEntity::getItemName, keyObj.toString());
            }
        }

        IPage<MaItemEntity> page = this.page(
                new Query<MaItemEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public boolean saveMaItem(MaItemEntity maItem) {
        List<MaItemEntity> list = this.baseMapper.selectList(
                new QueryWrapper<MaItemEntity>().lambda().eq(MaItemEntity::getItemName, maItem.getItemName()));

        if (!list.isEmpty()) {
            throw new RRException("错误:该维修项目已存在,请勿重复创建!", 501);
        }

        return this.baseMapper.insert(maItem) == 1;
    }

    @Override
    public boolean updateMaItemById(MaItemEntity maItem) {
        List<MaItemEntity> list = this.baseMapper.selectList(
                new QueryWrapper<MaItemEntity>()
                        .lambda()
                        .eq(MaItemEntity::getItemName, maItem.getItemName())
                        .ne(MaItemEntity::getMaItemId, maItem.getMaItemId()));

        if (!list.isEmpty()) {
            throw new RRException("错误:该维修项目已存在,请勿重复创建!", 501);
        }

        return this.baseMapper.updateById(maItem) == 1;
    }

}