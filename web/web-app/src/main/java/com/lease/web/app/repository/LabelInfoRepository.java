package com.lease.web.app.repository;

import com.lease.model.entity.LabelInfo;
import com.lease.web.app.vo.room.RoomLabelVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LabelInfoRepository extends JpaRepository<LabelInfo, Long> {

    @Query("""
    SELECT new com.lease.web.app.vo.room.RoomLabelVo(r.id, li.id, li.type ,li.name)
    FROM RoomInfo r
    JOIN r.labelInfoList li
    WHERE r.id IN :roomIds
""")
    List<RoomLabelVo> findRoomLabelVoByRoomIds(List<Long> roomIds);

}
