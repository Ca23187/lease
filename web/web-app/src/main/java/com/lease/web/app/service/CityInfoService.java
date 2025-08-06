package com.lease.web.app.service;

import com.lease.model.entity.CityInfo;

import java.util.List;

/**
* @description 针对表【city_info】的数据库操作Service
*/
public interface CityInfoService {

    List<CityInfo> findAllByProvinceId(Long id);
}
