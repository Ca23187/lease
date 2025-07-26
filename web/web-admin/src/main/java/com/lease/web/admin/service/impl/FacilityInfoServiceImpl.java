package com.lease.web.admin.service.impl;

import com.lease.model.entity.FacilityInfo;
import com.lease.model.enums.ItemType;
import com.lease.web.admin.repository.FacilityInfoRepository;
import com.lease.web.admin.service.FacilityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description 针对表【facility_info(配套信息表)】的数据库操作Service实现
*/
@Service
public class FacilityInfoServiceImpl implements FacilityInfoService {

    private final FacilityInfoRepository repository;

    @Autowired
    public FacilityInfoServiceImpl(FacilityInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FacilityInfo> list(ItemType type) {
        if (type != null) {
            return repository.findByType(type);
        }
        return repository.findAll();
    }

    @Override
    public void saveAndUpload(FacilityInfo facilityInfo) {
        repository.save(facilityInfo);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}




