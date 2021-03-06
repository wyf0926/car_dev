package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.renren.common.exception.RRException;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.vo.DictItemVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysDictItemDao;
import io.renren.modules.sys.entity.SysDictItemEntity;
import io.renren.modules.sys.service.SysDictItemService;


@Service("sysDictItemService")
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemDao, SysDictItemEntity> implements SysDictItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysDictItemEntity> page = this.page(
                new Query<SysDictItemEntity>().getPage(params),
                new QueryWrapper<SysDictItemEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryItemPage(Map<String, Object> params) {
        Long dictId = Long.valueOf(params.get("dictId").toString());

        IPage<SysDictItemEntity> page = this.page(
                new Query<SysDictItemEntity>().getPage(params),
                new QueryWrapper<SysDictItemEntity>().lambda().eq(SysDictItemEntity::getDictId,dictId)
        );

        return new PageUtils(page);
    }

    @Override
    public List<DictItemVo> queryItemList(Long dictId) {
        return this.baseMapper.selectItemList(dictId);
    }

    @Override
    public boolean saveDictItem(SysDictItemEntity sysDictItem) {
        List<SysDictItemEntity> sysDictNames = this.list(new QueryWrapper<SysDictItemEntity>().lambda()
                .eq(SysDictItemEntity::getItemText, sysDictItem.getItemText())
                .eq(SysDictItemEntity::getDictId, sysDictItem.getDictId()));
        List<SysDictItemEntity> sysDictCodes = this.list(new QueryWrapper<SysDictItemEntity>().lambda()
                .eq(SysDictItemEntity::getItemValue, sysDictItem.getItemValue())
                .eq(SysDictItemEntity::getDictId, sysDictItem.getDictId()));
        if (!sysDictNames.isEmpty() || !sysDictCodes.isEmpty()) {
            throw new RRException("错误：该字典项已存在，请勿重复创建！", 501);
        }
        return this.save(sysDictItem);
    }

    @Override
    public boolean updateDictItemById(SysDictItemEntity sysDictItem) {
        List<SysDictItemEntity> dictItemTexts = this.list(new LambdaQueryWrapper<SysDictItemEntity>()
                .eq(SysDictItemEntity::getItemText, sysDictItem.getItemText())
                .eq(SysDictItemEntity::getDictId, sysDictItem.getDictId())
                .ne(SysDictItemEntity::getId, sysDictItem.getId()));
        List<SysDictItemEntity> dictItemValues = this.list(new LambdaQueryWrapper<SysDictItemEntity>()
                .eq(SysDictItemEntity::getItemValue, sysDictItem.getItemValue())
                .eq(SysDictItemEntity::getDictId, sysDictItem.getDictId())
                .ne(SysDictItemEntity::getId, sysDictItem.getId()));
        if (!dictItemTexts.isEmpty() || !dictItemValues.isEmpty()) {
            throw new RRException("错误：该字典项已存在，请重新修改！", 501);
        }
        return this.updateById(sysDictItem);
    }

    @Override
    public List<DictItemVo> queryItemListByCode(String dictCode) {
        return this.baseMapper.selectItemListByCode(dictCode);
    }

}