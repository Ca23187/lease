package com.lease.web.admin.service;

import com.lease.model.entity.LeaseAgreement;
import com.lease.model.enums.LeaseStatus;
import com.lease.web.admin.dto.lease.AgreementQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
* @description 针对表【lease_agreement(租约信息表)】的数据库操作Service
*/
public interface LeaseAgreementService {

    void saveOrUpdate(LeaseAgreement leaseAgreement);

    Page<LeaseAgreement> pageViewAgreements(AgreementQueryDto queryDto, Pageable pageable);

    LeaseAgreement findById(Long id);

    void removeById(Long id);

    void updateStatusById(Long id, LeaseStatus status);
}
