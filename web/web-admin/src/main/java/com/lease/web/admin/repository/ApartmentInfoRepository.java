package com.lease.web.admin.repository;

import com.lease.model.entity.ApartmentInfo;
import com.lease.web.admin.controller.projection.apartment.ApartmentItemVoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApartmentInfoRepository extends JpaRepository<ApartmentInfo, Long> {

    @Query(
            value = """
                SELECT
                    ai.id AS id,
                    ai.name AS name,
                    ai.introduction AS introduction,
                    ai.district_id AS districtId,
                    ai.district_name AS districtName,
                    ai.city_id AS cityId,
                    ai.city_name AS cityName,
                    ai.province_id AS provinceId,
                    ai.province_name AS provinceName,
                    ai.address_detail AS addressDetail,
                    ai.latitude AS latitude,
                    ai.longitude AS longitude,
                    ai.phone AS phone,
                    ai.is_release AS isRelease,
                    IFNULL(tc.cnt, 0) AS totalRoomCount,
                    IFNULL(tc.cnt, 0) - IFNULL(cc.cnt, 0) AS freeRoomCount
                FROM (
                    SELECT * FROM apartment_info 
                    WHERE is_deleted = 0
                    AND (:provinceId IS NULL OR province_id = :provinceId)
                    AND (:cityId IS NULL OR city_id = :cityId)
                    AND (:districtId IS NULL OR district_id = :districtId)
                ) ai
                LEFT JOIN (
                    SELECT apartment_id, COUNT(*) AS cnt 
                    FROM room_info 
                    WHERE is_deleted = 0 
                    GROUP BY apartment_id
                ) tc ON ai.id = tc.apartment_id
                LEFT JOIN (
                    SELECT apartment_id, COUNT(*) AS cnt 
                    FROM lease_agreement 
                    WHERE is_deleted = 0 AND status IN (2, 5)
                    GROUP BY apartment_id
                ) cc ON ai.id = cc.apartment_id
                """,
            countQuery = """
                SELECT COUNT(*) 
                FROM apartment_info ai
                WHERE is_deleted = 0
                AND (:provinceId IS NULL OR province_id = :provinceId)
                AND (:cityId IS NULL OR city_id = :cityId)
                AND (:districtId IS NULL OR district_id = :districtId)
                """,
            nativeQuery = true
    )
    Page<ApartmentItemVoProjection> pageApartmentItemProjections(@Param("provinceId") Long provinceId,
                                                                 @Param("cityId") Long cityId,
                                                                 @Param("districtId") Long districtId,
                                                                 Pageable pageable);

    @Modifying
    @Query(value = "UPDATE apartment_info SET is_release = :status WHERE id = :id", nativeQuery = true)
        void updateReleaseStatusById(Long id, Integer status);

    List<ApartmentInfo> findByDistrictId(Long districtId);

}

