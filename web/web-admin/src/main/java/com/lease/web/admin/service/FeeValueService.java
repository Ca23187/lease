package com.lease.web.admin.service;

import com.lease.model.entity.FeeValue;

/**
* @description 针对表【fee_value(杂项费用值表)】的数据库操作Service
*/
public interface FeeValueService {

    void saveOrUpdate(FeeValue feeValue);

    void deleteById(Long id);
}
