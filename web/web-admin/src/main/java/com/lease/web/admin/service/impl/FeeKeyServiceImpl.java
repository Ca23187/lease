package com.lease.web.admin.service.impl;

import com.lease.model.entity.FeeKey;
import com.lease.model.entity.FeeValue;
import com.lease.web.admin.repository.FeeKeyRepository;
import com.lease.web.admin.service.FeeKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description 针对表【fee_key(杂项费用名称表)】的数据库操作Service实现
*/
@Service
public class FeeKeyServiceImpl implements FeeKeyService{

    private final FeeKeyRepository repository;

    @Autowired
    public FeeKeyServiceImpl(FeeKeyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveOrUpdate(FeeKey feeKey) {
        repository.save(feeKey);
    }

    @Override
    public void deleteById(Long feeKeyId) {
        repository.deleteById(feeKeyId);
    }

    @Override
    public List<FeeKey> feeInfoList() {
        List<FeeKey> feeKeyList = repository.findAllWithValues();
        for (FeeKey feeKey : feeKeyList) {
            for (FeeValue feeValue : feeKey.getFeeValueList()) {
                feeValue.setFeeKeyId(feeKey.getId());
            }
        }
        return feeKeyList;
    }
}




