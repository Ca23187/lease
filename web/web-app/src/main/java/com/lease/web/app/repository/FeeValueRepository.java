package com.lease.web.app.repository;

import com.lease.model.entity.FeeValue;
import com.lease.web.app.vo.fee.FeeValueVo;
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
    WHERE fv.id IN (
          SELECT afv.feeValueId
          FROM ApartmentFeeValue afv
          WHERE afv.apartmentId = :apartmentId
      )
""")
    List<FeeValueVo> findAllFeeValueVoByApartmentId(Long apartmentId);

}
