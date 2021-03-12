package io.renren.modules.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.business.dao.PartDao;
import io.renren.modules.business.entity.PartEntity;
import io.renren.modules.business.service.PartService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Map;

/**
 *
 * @author allan
 * @email zwy1997213@163.com
 * @date 2021-02-21 11:09:31
 */

@Service("partService")
public class PartServiceImpl extends ServiceImpl<PartDao, PartEntity> implements PartService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<PartEntity> queryWrapper = new QueryWrapper<>();

        Object keyObj = params.get("key");

        if (keyObj != null) {
            if (StringUtils.isNotBlank(keyObj.toString())) {
                queryWrapper.lambda().like(PartEntity::getName, keyObj.toString());
            }
        }

        IPage<PartEntity> page = this.page(
                new Query<PartEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public boolean savePart(PartEntity part) {
        List<PartEntity> list = this.baseMapper.selectList(
                new QueryWrapper<PartEntity>().lambda().eq(PartEntity::getName, part.getName()));

        if (!list.isEmpty()) {
            throw new RRException("错误:该配件已存在,请勿重复创建!", 501);
        }

        return this.baseMapper.insert(part) == 1;
    }

    @Override
    public boolean updatePartById(PartEntity part) {
        List<PartEntity> list = this.baseMapper.selectList(
                new QueryWrapper<PartEntity>()
                        .lambda()
                        .eq(PartEntity::getName, part.getName())
                        .ne(PartEntity::getPartId, part.getPartId()));

        if (!list.isEmpty()) {
            throw new RRException("错误:该配件已存在,请勿重复创建!", 501);
        }

        return this.baseMapper.updateById(part) == 1;
    }
}