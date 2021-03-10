package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.SysDictDao;
import io.renren.modules.sys.dao.SysDictItemDao;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.entity.SysDictItemEntity;
import io.renren.modules.sys.service.SysDictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements SysDictService {

    @Resource
    private SysDictItemDao sysDictItemDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<SysDictEntity> sysDictEntityQueryWrapper = new QueryWrapper<>();

        Object nameObj = params.get("dictName");
        if (nameObj != null) {
            String dictName = nameObj.toString();
            if (StringUtils.isNotBlank(dictName)) {
                sysDictEntityQueryWrapper.lambda().like(SysDictEntity::getDictName, dictName);
            }
        }

        Object codeObj = params.get("dictCode");
        if (codeObj != null) {
            String dictCode = codeObj.toString();
            if (StringUtils.isNotBlank(dictCode)) {
                sysDictEntityQueryWrapper.lambda().like(SysDictEntity::getDictCode, dictCode);
            }
        }

        IPage<SysDictEntity> page = this.page(
                new Query<SysDictEntity>().getPage(params),
                sysDictEntityQueryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public boolean saveSysDictItem(SysDictEntity sysDict) {
        List<SysDictEntity> dictNames = this.list(new QueryWrapper<SysDictEntity>()
                .lambda().eq(SysDictEntity::getDictName, sysDict.getDictName()));
        List<SysDictEntity> dictCodes = this.list(new QueryWrapper<SysDictEntity>()
                .lambda().eq(SysDictEntity::getDictCode, sysDict.getDictCode()));
        if (!dictNames.isEmpty() || !dictCodes.isEmpty()) {
            throw new RRException("错误：该字典已存在，请勿重复创建！", 501);
        }
        return this.save(sysDict);
    }

    @Override
    public boolean updateDictById(SysDictEntity sysDict) {
        List<SysDictEntity> dictNames = this.list(new LambdaQueryWrapper<SysDictEntity>().
                eq(SysDictEntity::getDictName, sysDict.getDictName()).ne(SysDictEntity::getId, sysDict.getId()));
        List<SysDictEntity> dictCodes = this.list(new LambdaQueryWrapper<SysDictEntity>().
                eq(SysDictEntity::getDictCode, sysDict.getDictCode()).ne(SysDictEntity::getId, sysDict.getId()));
        if (!dictNames.isEmpty() || !dictCodes.isEmpty()) {
            throw new RRException("错误：该字典已存在，请重新修改！", 501);
        }
        return this.updateById(sysDict);
    }

    @Override
    public boolean removeSysDictByIds(List<Long> list) {
        List<SysDictItemEntity> sysDictItems = sysDictItemDao.selectList(new LambdaQueryWrapper<SysDictItemEntity>()
                .in(SysDictItemEntity::getDictId, list));
        if (!sysDictItems.isEmpty()) {
            throw new RRException("删除失败，请先删除该字典里的字典项！", 501);
        }

        return this.baseMapper.deleteBatchIds(list) == list.size();
    }

}