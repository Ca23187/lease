package com.lease.web.admin.service.impl;

import com.lease.model.entity.AttrKey;
import com.lease.model.entity.AttrValue;
import com.lease.web.admin.repository.AttrKeyRepository;
import com.lease.web.admin.service.AttrKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Service实现
*/
@Service
public class AttrKeyServiceImpl implements AttrKeyService {

    private final AttrKeyRepository repository;

    @Autowired
    public AttrKeyServiceImpl(AttrKeyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveOrUpdate(AttrKey attrKey) {
        repository.save(attrKey);
    }

    @Override
    public List<AttrKey> listAttrInfo() {
        List<AttrKey> attrKeyList = repository.findAllWithValues();
        for (AttrKey attrKey : attrKeyList) {
            for (AttrValue attrValue : attrKey.getAttrValueList()) {
                attrValue.setAttrKeyId(attrKey.getId());
            }
        }
        return attrKeyList;
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}




