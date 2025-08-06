package com.lease.web.app.repository;

import com.lease.model.entity.ApartmentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApartmentInfoRepository extends JpaRepository<ApartmentInfo, Long> {

    @Query("""
    select ai
    from ApartmentInfo ai
    left join fetch ai.facilityInfoList
    where ai.id = :id
    """)
    ApartmentInfo findWithFacilityListById(Long id);
}
