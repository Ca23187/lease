package com.lease.web.admin.service.impl;

import com.lease.model.entity.AttrValue;
import com.lease.web.admin.repository.AttrValueRepository;
import com.lease.web.admin.service.AttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @description 针对表【attr_value(房间基本属性值表)】的数据库操作Service实现
*/
@Service
public class AttrValueServiceImpl implements AttrValueService {

    private final AttrValueRepository repository;

    @Autowired
    public AttrValueServiceImpl(AttrValueRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveOrUpdate(AttrValue attrValue) {
        repository.save(attrValue);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}




