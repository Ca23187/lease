package com.lease.web.admin.repository;

import com.lease.model.entity.GraphInfo;
import com.lease.model.enums.ItemType;
import com.lease.web.admin.dto.graph.GraphVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GraphInfoRepository extends JpaRepository<GraphInfo, Long> {
    @Modifying
    @Query("update GraphInfo g set g.isDeleted = 1, g.updateTime = current_timestamp where g.itemId = :id and g.itemType = com.lease.model.enums.ItemType.APARTMENT")
    void deleteApartmentGraphByItemId(Long id);

    @Modifying
    @Query("update GraphInfo g set g.isDeleted = 1, g.updateTime = current_timestamp where g.itemId = :id and g.itemType = com.lease.model.enums.ItemType.ROOM")
    void deleteRoomGraphByItemId(Long id);

    @Query("""
        select
            g.name as name,
            g.url as url
        from GraphInfo g
        where g.isDeleted = 0
        and g.itemType = :itemType
        and g.itemId = :id
    """)
    List<GraphVo> findAllGraphVoById(Long id, ItemType itemType);
}
