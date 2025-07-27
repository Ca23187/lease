package com.lease.web.admin.service.impl;

import com.lease.model.entity.FeeKey;
import com.lease.web.admin.repository.FeeKeyRepository;
import com.lease.web.admin.service.FeeKeyService;
import com.lease.web.admin.vo.fee.FeeKeyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<FeeKeyVo> feeInfoList() {
        return repository.findAllWithValues().stream()
                .map(k -> new FeeKeyVo(k.getId(), k.getName(), k.getFeeValueList()))
                .collect(Collectors.toList());
    }
}




