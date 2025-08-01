package com.lease.web.admin.repository;

import com.lease.model.entity.AttrValue;
import com.lease.web.admin.vo.attr.AttrValueVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttrValueRepository extends JpaRepository<AttrValue, Long> {
    @Query("""
    SELECT av.id AS id,
           av.name AS name,
           ak.id AS attrKeyId,
           ak.name AS attrKeyName
    FROM AttrValue av
    JOIN av.attrKey ak
    WHERE av.isDeleted = 0
      AND ak.isDeleted = 0
      AND av.id IN (
          SELECT avl.id
          FROM RoomInfo r
          JOIN r.attrValueList avl
          WHERE r.id = :apartmentId
      )
""")
    List<AttrValueVo> findAllAttrValueVoByApartmentId(Long apartmentId);
}
