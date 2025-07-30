package com.lease.web.admin.service;

import com.lease.model.entity.DistrictInfo;

import java.util.List;

/**
* @description 针对表【district_info】的数据库操作Service
*/
public interface DistrictInfoService {

    List<DistrictInfo> findAllByCityId(Long id);
}
