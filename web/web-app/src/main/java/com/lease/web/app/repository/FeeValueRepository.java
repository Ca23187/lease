package com.lease.web.app.repository;

import com.lease.model.entity.FeeValue;
import com.lease.web.app.vo.fee.FeeValueVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeeValueRepository extends JpaRepository<FeeValue, Long> {
    @Query("""
    SELECT new com.lease.web.app.vo.fee.FeeValueVo(
        fv.id,
        fv.name,
        fv.unit,
        fk.id,
        fk.name
    )
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
