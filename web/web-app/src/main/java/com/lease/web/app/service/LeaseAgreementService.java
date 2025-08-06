package com.lease.web.app.service;

import com.lease.model.entity.LeaseAgreement;
import com.lease.model.enums.LeaseStatus;
import com.lease.web.app.vo.agreement.AgreementDetailVo;
import com.lease.web.app.vo.agreement.AgreementItemVo;

import java.util.List;

/**
* @description 针对表【lease_agreement(租约信息表)】的数据库操作Service
*/
public interface LeaseAgreementService {
    void saveOrUpdate(LeaseAgreement leaseAgreement);

    void updateStatusByValue(Long id, LeaseStatus leaseStatus);

    AgreementDetailVo getDetailById(Long id);

    List<AgreementItemVo> listItem();
}
