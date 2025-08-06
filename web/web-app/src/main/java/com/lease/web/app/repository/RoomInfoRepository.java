package com.lease.web.app.repository;

import com.lease.model.entity.PaymentType;
import com.lease.model.entity.RoomInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface RoomInfoRepository extends JpaRepository<RoomInfo, Long> {
    @Query("""
    select r.paymentTypeList
    from RoomInfo r
    where r.id = :id
    """)
    List<PaymentType> findPaymentTypeListById(Long id);

    @Query(value = """
    select distinct r
    from RoomInfo r
    left join fetch r.apartmentInfo
    where r.isRelease = com.lease.model.enums.ReleaseStatus.RELEASED
    and r.id not in (
        select la.roomInfo.id from LeaseAgreement la where la.status in (com.lease.model.enums.LeaseStatus.SIGNED, com.lease.model.enums.LeaseStatus.WITHDRAWING)
    )
    and (:provinceId is null or r.apartmentInfo.provinceId = :provinceId)
    and (:cityId is null or r.apartmentInfo.cityId = :cityId)
    and (:districtId is null or r.apartmentInfo.districtId = :districtId)
    and (:minRent is null or r.rent >= :minRent)
    and (:maxRent is null or r.rent <= :maxRent)
    and (:paymentTypeId is null or r.id in (
        select r2.roomId
        from RoomPaymentType r2
        where r2.paymentTypeId = :paymentTypeId
    ))
    """, countQuery = """
    select count(distinct r)
    from RoomInfo r
    where (:provinceId is null or r.apartmentInfo.provinceId = :provinceId)
        and (:cityId is null or r.apartmentInfo.cityId = :cityId)
        and (:districtId is null or r.apartmentInfo.districtId = :districtId)
        and (:minRent is null or r.rent >= :minRent)
        and (:maxRent is null or r.rent <= :maxRent)
        and (:paymentTypeId is null or r.id in (
            select r2.roomId
            from RoomPaymentType r2
            where r2.paymentTypeId = :paymentTypeId
    ))
    """)
    Page<RoomInfo> pageItem(Long provinceId, Long cityId, Long districtId, BigDecimal minRent, BigDecimal maxRent, Long paymentTypeId, Pageable pageable);

    @Query(value = """
    select distinct r
    from RoomInfo r
    left join fetch r.apartmentInfo
    where r.isRelease = com.lease.model.enums.ReleaseStatus.RELEASED
    and r.id not in (
        select la.roomInfo.id from LeaseAgreement la where la.status in (com.lease.model.enums.LeaseStatus.SIGNED, com.lease.model.enums.LeaseStatus.WITHDRAWING)
    )
    and r.apartmentInfo.id = :id
    """, countQuery = """
    select count(r)
    from RoomInfo r
    where r.isRelease = com.lease.model.enums.ReleaseStatus.RELEASED
    and r.id not in (
        select la.roomInfo.id from LeaseAgreement la where la.status in (com.lease.model.enums.LeaseStatus.SIGNED, com.lease.model.enums.LeaseStatus.WITHDRAWING)
    )
    and r.apartmentInfo.id = :id
    """)
    Page<RoomInfo> pageItemByApartmentId(Long id, Pageable pageable);

    @Query("""
    select distinct r
    from RoomInfo r
    left join fetch r.apartmentInfo
    left join fetch r.facilityInfoList
    where r.id = :id
    """)
    RoomInfo getDetailById(Long id);

    @Query("""
    select min(r.rent)
    from RoomInfo r
    where r.apartmentInfo.id = :id
    """)
    BigDecimal findMinRentByApartmentInfo_Id(Long id);
}
