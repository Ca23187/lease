package com.lease.web.admin.repository;

import com.lease.model.entity.LeaseAgreement;
import com.lease.model.enums.LeaseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface LeaseAgreementRepository extends JpaRepository<LeaseAgreement, Long> {

    @Query("""
    select la from LeaseAgreement la
    left join fetch la.apartmentInfo ai
    left join fetch la.roomInfo ri
    left join fetch la.paymentType pt
    left join fetch la.leaseTerm lt
    where (:provinceId is null or ai.provinceId = :provinceId)
        and (:cityId is null or ai.cityId = :cityId)
        and (:districtId is null or ai.districtId = :districtId)
        and (:apartmentId is null or ai.id = :apartmentId)
        and (:roomNumber is null or ri.roomNumber like concat('%', :roomNumber, '%'))
        and (:name is null or la.name like concat('%', :name, '%'))
        and (:phone is null or la.phone like concat('%', :phone, '%'))
    """)
    Page<LeaseAgreement> findByQuery(
            String phone,
            String name,
            String roomNumber,
            Long apartmentId,
            Long provinceId,
            Long cityId,
            Long districtId,
            Pageable pageable
    );

    @Query("""
    select la from LeaseAgreement la
    left join fetch la.apartmentInfo ai
    left join fetch la.roomInfo ri
    left join fetch la.paymentType pt
    left join fetch la.leaseTerm lt
    where la.id = :id
    """)
    Optional<LeaseAgreement> findById(Long id);

    @Modifying
    @Query("UPDATE LeaseAgreement SET status = :status WHERE id = :id")
    void updateStatusById(Long id, LeaseStatus status);

    @Modifying
    @Query("""
    UPDATE LeaseAgreement la
    SET la.status = com.lease.model.enums.LeaseStatus.EXPIRED
    WHERE la.leaseEndDate <= :now
      AND la.status IN (com.lease.model.enums.LeaseStatus.SIGNED, com.lease.model.enums.LeaseStatus.WITHDRAWING)
    """)
    void updateExpiredAgreements(Date now);
}
