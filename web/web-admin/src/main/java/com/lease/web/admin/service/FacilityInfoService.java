package com.lease.web.admin.service;

import com.lease.model.entity.FacilityInfo;
import com.lease.model.enums.ItemType;

import java.util.List;

/**
* @description 针对表【facility_info(配套信息表)】的数据库操作Service
*/
public interface FacilityInfoService {

    List<FacilityInfo> list(ItemType type);

    void saveOrUpload(FacilityInfo facilityInfo);

    void removeById(Long id);
}
