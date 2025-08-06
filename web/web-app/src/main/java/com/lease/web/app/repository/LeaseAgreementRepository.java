package com.lease.web.app.repository;

import com.lease.model.entity.LeaseAgreement;
import com.lease.model.enums.LeaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaseAgreementRepository extends JpaRepository<LeaseAgreement, Long> {
    @Query("update LeaseAgreement l set l.status = :status, l.updateTime = current_timestamp where l.id = :id")
    @Modifying
    void updateStatusById(LeaseStatus status, Long id);

    @Query("""
    select l
    from LeaseAgreement l
    left join fetch l.apartmentInfo
    left join fetch l.roomInfo
    left join fetch l.leaseTerm
    left join fetch l.paymentType
    where l.id = :id
    """)
    LeaseAgreement findWithAllValuesById(Long id);

    @Query("""
    select l
    from LeaseAgreement l
    left join fetch l.apartmentInfo a
    left join fetch l.roomInfo r
    """)
    List<LeaseAgreement> findList();
}
