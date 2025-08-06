package com.lease.web.admin.service;

import com.lease.model.entity.LabelInfo;
import com.lease.model.enums.ItemType;

import java.util.List;

/**
* @description 针对表【label_info(标签信息表)】的数据库操作Service
*/
public interface LabelInfoService {
    List<LabelInfo> list(ItemType type);

    void saveOrUpdate(LabelInfo labelInfo);

    void removeById(Long id);
}
