package com.lease.web.admin.service.impl;

import com.lease.model.entity.LeaseAgreement;
import com.lease.model.enums.LeaseStatus;
import com.lease.web.admin.dto.lease.AgreementQueryDto;
import com.lease.web.admin.repository.LeaseAgreementRepository;
import com.lease.web.admin.service.LeaseAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description 针对表【lease_agreement(租约信息表)】的数据库操作Service实现
 */
@Service
public class LeaseAgreementServiceImpl implements LeaseAgreementService {

    private final LeaseAgreementRepository repository;

    @Autowired
    public LeaseAgreementServiceImpl(LeaseAgreementRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void saveOrUpdate(LeaseAgreement leaseAgreement) {
        repository.save(leaseAgreement);
    }

    @Override
    public Page<LeaseAgreement> pageViewAgreements(AgreementQueryDto queryDto, Pageable pageable) {
        Page<LeaseAgreement> page = repository.findByQuery(
            queryDto.getPhone(),
            queryDto.getName(),
            queryDto.getRoomNumber(),
            queryDto.getApartmentId(),
            queryDto.getProvinceId(),
            queryDto.getCityId(),
            queryDto.getDistrictId(),
            pageable
        );
        for (LeaseAgreement leaseAgreement : page.getContent()) {
            leaseAgreement.setApartmentId(leaseAgreement.getApartmentInfo().getId());
            leaseAgreement.setRoomId(leaseAgreement.getRoomInfo().getId());
            leaseAgreement.setPaymentTypeId(leaseAgreement.getPaymentType().getId());
            leaseAgreement.setLeaseTermId(leaseAgreement.getLeaseTerm().getId());
            leaseAgreement.getRoomInfo().setApartmentId(leaseAgreement.getApartmentInfo().getId());
        }
        return page;
    }

    @Override
    public LeaseAgreement findById(Long id) {
        LeaseAgreement leaseAgreement = repository.findById(id).orElseThrow(() -> new RuntimeException("未找到指定的租约信息"));
        leaseAgreement.setApartmentId(leaseAgreement.getApartmentInfo().getId());
        leaseAgreement.setRoomId(leaseAgreement.getRoomInfo().getId());
        leaseAgreement.setPaymentTypeId(leaseAgreement.getPaymentType().getId());
        leaseAgreement.setLeaseTermId(leaseAgreement.getLeaseTerm().getId());
        leaseAgreement.getRoomInfo().setApartmentId(leaseAgreement.getApartmentInfo().getId());
        return leaseAgreement;
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateStatusById(Long id, LeaseStatus status) {
        repository.updateStatusById(id, status);
    }
}




