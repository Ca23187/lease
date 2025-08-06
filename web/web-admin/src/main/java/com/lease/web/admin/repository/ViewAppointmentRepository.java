package com.lease.web.admin.repository;

import com.lease.model.entity.ViewAppointment;
import com.lease.model.enums.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ViewAppointmentRepository extends JpaRepository<ViewAppointment, Long> {

    @Modifying
    @Query(value = "UPDATE ViewAppointment SET appointmentStatus = :status, updateTime = current_timestamp WHERE id = :id")
    void updateStatusById(Long id, AppointmentStatus status);

    @Query(value = """
        SELECT va FROM ViewAppointment va
        LEFT JOIN FETCH va.apartmentInfo ai
        WHERE (:provinceId IS NULL OR ai.provinceId = :provinceId)
          AND (:cityId IS NULL OR ai.cityId = :cityId)
          AND (:districtId IS NULL OR ai.districtId = :districtId)
          AND (:apartmentId IS NULL OR ai.id = :apartmentId)
          AND (:name IS NULL OR va.name LIKE %:name%)
          AND (:phone IS NULL OR va.phone LIKE %:phone%)
    """, countQuery = """
        SELECT COUNT(va) FROM ViewAppointment va
        LEFT JOIN va.apartmentInfo ai
        WHERE (:provinceId IS NULL OR ai.provinceId = :provinceId)
          AND (:cityId IS NULL OR ai.cityId = :cityId)
          AND (:districtId IS NULL OR ai.districtId = :districtId)
          AND (:apartmentId IS NULL OR ai.id = :apartmentId)
          AND (:name IS NULL OR va.name LIKE %:name%)
          AND (:phone IS NULL OR va.phone LIKE %:phone%)
    """)
    Page<ViewAppointment> findByQuery(
            Long provinceId,
            Long cityId,
            Long districtId,
            Long apartmentId,
            String name,
            String phone,
            Pageable pageable
    );
}
