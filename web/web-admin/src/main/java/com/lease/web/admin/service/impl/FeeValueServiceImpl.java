package com.lease.web.admin.service.impl;

import com.lease.model.entity.FeeValue;
import com.lease.web.admin.repository.FeeValueRepository;
import com.lease.web.admin.service.FeeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @description 针对表【fee_value(杂项费用值表)】的数据库操作Service实现
*/
@Service
public class FeeValueServiceImpl implements FeeValueService{

    private final FeeValueRepository repository;

    @Autowired
    public FeeValueServiceImpl(FeeValueRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveOrUpdate(FeeValue feeValue) {
        repository.save(feeValue);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}




