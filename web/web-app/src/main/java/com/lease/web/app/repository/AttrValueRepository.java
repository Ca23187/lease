package com.lease.web.app.repository;

import com.lease.model.entity.AttrValue;
import com.lease.web.app.vo.attr.AttrValueVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttrValueRepository extends JpaRepository<AttrValue, Long> {
    @Query("""
    SELECT new com.lease.web.app.vo.attr.AttrValueVo(
        av.id,
        av.name,
        ak.id,
        ak.name
    )
    FROM AttrValue av
    JOIN av.attrKey ak
    WHERE av.id IN (
          SELECT rav.attrValueId
          FROM RoomAttrValue rav
          WHERE rav.roomId = :roomId
      )
""")
    List<AttrValueVo> findAllAttrValueVoByRoomId(Long roomId);
}
