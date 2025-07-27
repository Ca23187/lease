package com.lease.web.admin.service;

import com.lease.model.entity.FeeKey;
import com.lease.web.admin.vo.fee.FeeKeyVo;

import java.util.List;

/**
* @description 针对表【fee_key(杂项费用名称表)】的数据库操作Service
*/
public interface FeeKeyService {

    void saveOrUpdate(FeeKey feeKey);

    void deleteById(Long feeKeyId);

    List<FeeKeyVo> feeInfoList();
}
