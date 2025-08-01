package com.lease.web.admin.service;

import com.lease.model.entity.AttrKey;

import java.util.List;

/**
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Service
*/
public interface AttrKeyService{

    void saveOrUpdate(AttrKey attrKey);

    List<AttrKey> listAttrInfo();

    void removeById(Long id);
}
