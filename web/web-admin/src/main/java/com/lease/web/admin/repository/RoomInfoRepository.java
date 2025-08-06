package com.lease.web.admin.repository;

import com.lease.model.entity.RoomInfo;
import com.lease.model.enums.ReleaseStatus;
import com.lease.web.admin.dto.room.RoomQueryDto;
import com.lease.web.admin.vo.room.RoomInfoVo;
import com.lease.web.admin.vo.room.RoomItemVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomInfoRepository extends JpaRepository<RoomInfo, Long> {
    @Query(value = "SELECT COUNT(r) FROM RoomInfo r WHERE r.apartmentInfo.id = :id")
    Long countByApartmentInfoId(Long id);

    @Query(value = """
    select
        ri.id as id,
        ri.roomNumber as roomNumber,
        ri.rent as rent,
        ri.apartmentInfo.id as apartmentId,
        ri.isRelease as isRelease,
        case when la.id is not null then true else false end as isCheckIn,
        la.leaseEndDate as leaseEndDate,
        ai as apartmentInfo
    from RoomInfo ri
    left join LeaseAgreement la on la.roomInfo.id = ri.id
        and la.status in (com.lease.model.enums.LeaseStatus.SIGNED, com.lease.model.enums.LeaseStatus.WITHDRAWING)
    left join ApartmentInfo ai on ri.apartmentInfo.id = ai.id
    where (:#{#vo.provinceId} is null or ai.provinceId = :#{#vo.provinceId})
        and (:#{#vo.cityId} is null or ai.cityId = :#{#vo.cityId})
        and (:#{#vo.districtId} is null or ai.districtId = :#{#vo.districtId})
        and (:#{#vo.apartmentId} is null or ri.apartmentInfo.id = :#{#vo.apartmentId})
    """, countQuery = """
    select count(ri)
    from RoomInfo ri
    where (:#{#vo.provinceId} is null or ri.apartmentInfo.provinceId = :#{#vo.provinceId})
        and (:#{#vo.cityId} is null or ri.apartmentInfo.cityId = :#{#vo.cityId})
        and (:#{#vo.districtId} is null or ri.apartmentInfo.districtId = :#{#vo.districtId})
        and (:#{#vo.apartmentId} is null or ri.apartmentInfo.id = :#{#vo.apartmentId})
    """)
    Page<RoomItemVo> pageRoomItems(RoomQueryDto vo, Pageable pageable);

    @Query("SELECT r FROM RoomInfo r LEFT JOIN FETCH r.apartmentInfo WHERE r.id = :id")
    Optional<RoomInfo> findWithApartmentById(Long id);

    @Query("""
    SELECT
        r.id AS id,
        r.roomNumber AS roomNumber,
        r.rent AS rent,
        r.isRelease AS isRelease,
        r.apartmentInfo.id AS apartmentId
    FROM RoomInfo r
    """)
    List<RoomInfoVo> findAllBasicRoomInfoVo();

    @Modifying
    @Query(value = "UPDATE RoomInfo ri SET ri.isRelease = :status, ri.updateTime = current_timestamp WHERE ri.id = :id")
    void updateReleaseStatusById(Long id, ReleaseStatus status);
}
