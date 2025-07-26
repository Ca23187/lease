package com.lease.web.admin.service;

import com.lease.model.entity.AttrKey;
import com.lease.web.admin.vo.attr.AttrKeyVo;

import java.util.List;

/**
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Service
*/
public interface AttrKeyService{

    void saveAndUpdate(AttrKey attrKey);

    List<AttrKeyVo> listAttrInfo();

    void removeById(Long id);
}
