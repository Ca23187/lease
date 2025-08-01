package com.lease.web.admin.repository;

import com.lease.model.entity.FeeValue;
import com.lease.web.admin.vo.fee.FeeValueVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeeValueRepository extends JpaRepository<FeeValue, Long> {
    @Query("""
    SELECT fv.id AS id,
           fv.name AS name,
           fv.unit AS unit,
           fk.id AS feeKeyId,
           fk.name AS feeKeyName
    FROM FeeValue fv
    JOIN fv.feeKey fk
    WHERE fv.isDeleted = 0
      AND fk.isDeleted = 0
      AND fv.id IN (
          SELECT afv.id
          FROM ApartmentInfo a
          JOIN a.feeValueList afv
          WHERE a.id = :apartmentId
      )
""")
    List<FeeValueVo> findAllFeeValueVoByApartmentId(Long apartmentId);

}
