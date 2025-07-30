package com.lease.web.admin.service.impl;

import com.lease.model.entity.AttrKey;
import com.lease.web.admin.mapper.attr.AttrKeyMapper;
import com.lease.web.admin.repository.AttrKeyRepository;
import com.lease.web.admin.service.AttrKeyService;
import com.lease.web.admin.vo.attr.AttrKeyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Service实现
*/
@Service
public class AttrKeyServiceImpl implements AttrKeyService {

    private final AttrKeyRepository repository;
    private final AttrKeyMapper mapper;

    @Autowired
    public AttrKeyServiceImpl(AttrKeyRepository repository, AttrKeyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void saveOrUpdate(AttrKey attrKey) {
        repository.save(attrKey);
    }

    @Override
    public List<AttrKeyVo> listAttrInfo() {
        return mapper.toVoList(repository.findAllWithValues());
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}




