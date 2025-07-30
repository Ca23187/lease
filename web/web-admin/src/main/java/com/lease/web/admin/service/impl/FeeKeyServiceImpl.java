package com.lease.web.admin.service.impl;

import com.lease.model.entity.FeeKey;
import com.lease.web.admin.mapper.fee.FeeKeyMapper;
import com.lease.web.admin.repository.FeeKeyRepository;
import com.lease.web.admin.service.FeeKeyService;
import com.lease.web.admin.vo.fee.FeeKeyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description 针对表【fee_key(杂项费用名称表)】的数据库操作Service实现
*/
@Service
public class FeeKeyServiceImpl implements FeeKeyService{

    private final FeeKeyRepository repository;
    private final FeeKeyMapper feeKeyMapper;

    @Autowired
    public FeeKeyServiceImpl(FeeKeyRepository repository,
                             FeeKeyMapper feeKeyMapper) {
        this.repository = repository;
        this.feeKeyMapper = feeKeyMapper;
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
        return feeKeyMapper.toVoList(repository.findAllWithValues());
    }
}




