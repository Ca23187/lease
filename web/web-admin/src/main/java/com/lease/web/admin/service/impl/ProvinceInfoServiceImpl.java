package com.lease.web.admin.service.impl;

import com.lease.model.entity.CityInfo;
import com.lease.model.entity.ProvinceInfo;
import com.lease.web.admin.repository.ProvinceInfoRepository;
import com.lease.web.admin.service.ProvinceInfoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description 针对表【province_info】的数据库操作Service实现
*/
@Service
public class ProvinceInfoServiceImpl implements ProvinceInfoService{

    private final ProvinceInfoRepository repository;

    @Autowired
    public ProvinceInfoServiceImpl(ProvinceInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProvinceInfo> list() {
        return repository.findAll();
    }
}




