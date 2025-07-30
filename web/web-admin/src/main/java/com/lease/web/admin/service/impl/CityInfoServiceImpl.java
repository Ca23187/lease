package com.lease.web.admin.service.impl;

import com.lease.model.entity.CityInfo;
import com.lease.web.admin.repository.CityInfoRepository;
import com.lease.web.admin.service.CityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description 针对表【city_info】的数据库操作Service实现
*/
@Service
public class CityInfoServiceImpl implements CityInfoService{

    private final CityInfoRepository repository;

    @Autowired
    public CityInfoServiceImpl(CityInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CityInfo> findAllByProvinceId(Long id) {
        return repository.findAllByProvinceId(id);
    }
}




