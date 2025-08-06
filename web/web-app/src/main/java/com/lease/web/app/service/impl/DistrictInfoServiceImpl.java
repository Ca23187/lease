package com.lease.web.app.service.impl;

import com.lease.model.entity.DistrictInfo;
import com.lease.web.app.repository.DistrictInfoRepository;
import com.lease.web.app.service.DistrictInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description 针对表【district_info】的数据库操作Service实现
*/
@Service
public class DistrictInfoServiceImpl implements DistrictInfoService{

    private final DistrictInfoRepository repository;

    @Autowired
    public DistrictInfoServiceImpl(DistrictInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DistrictInfo> findAllByCityId(Long id) {
        return repository.findAllByCityId(id);
    }
}




