package com.lease.web.admin.service;

import com.lease.model.entity.CityInfo;
import com.lease.model.entity.ProvinceInfo;

import java.util.List;

/**
* @description 针对表【province_info】的数据库操作Service
*/
public interface ProvinceInfoService {

    List<ProvinceInfo> list();

    List<CityInfo> findAllById(Long id);
}
