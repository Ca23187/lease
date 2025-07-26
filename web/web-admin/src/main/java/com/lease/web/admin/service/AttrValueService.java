package com.lease.web.admin.service;

import com.lease.model.entity.AttrValue;

/**
* @description 针对表【attr_value(房间基本属性值表)】的数据库操作Service
*/
public interface AttrValueService{

    void saveAndUpdate(AttrValue attrValue);

    void removeById(Long id);
}
