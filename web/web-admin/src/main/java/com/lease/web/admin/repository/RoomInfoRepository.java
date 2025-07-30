package com.lease.web.admin.repository;

import com.lease.model.entity.RoomInfo;
import com.lease.web.admin.controller.projection.room.RoomItemVoProjection;
import com.lease.web.admin.vo.room.RoomQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomInfoRepository extends JpaRepository<RoomInfo, Long> {
    @Query(value = "SELECT COUNT(*) FROM room_info WHERE apartment_id = :id AND is_deleted = 0", nativeQuery = true)
    public Long countByApartmentInfoId(Long id);

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
    where ri.isDeleted = 0
        and (:#{#vo.provinceId} is null or ai.provinceId = :#{#vo.provinceId})
        and (:#{#vo.cityId} is null or ai.cityId = :#{#vo.cityId})
        and (:#{#vo.districtId} is null or ai.districtId = :#{#vo.districtId})
        and (:#{#vo.apartmentId} is null or ri.apartmentInfo.id = :#{#vo.apartmentId})
    """, countQuery = """
    select count(ri)
    from RoomInfo ri
    where ri.isDeleted = 0
        and (:#{#vo.provinceId} is null or ri.apartmentInfo.provinceId = :#{#vo.provinceId})
        and (:#{#vo.cityId} is null or ri.apartmentInfo.cityId = :#{#vo.cityId})
        and (:#{#vo.districtId} is null or ri.apartmentInfo.districtId = :#{#vo.districtId})
        and (:#{#vo.apartmentId} is null or ri.apartmentInfo.id = :#{#vo.apartmentId})
    """)
    Page<RoomItemVoProjection> pageRoomItemProjections(@Param("vo") RoomQueryVo vo, Pageable pageable);

    List<RoomInfo> findByApartmentInfo_Id(Long id);
}
