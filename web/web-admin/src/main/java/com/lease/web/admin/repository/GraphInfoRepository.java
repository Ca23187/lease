package com.lease.web.admin.repository;

import com.lease.model.entity.GraphInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GraphInfoRepository extends JpaRepository<GraphInfo, Long> {
    @Modifying
    @Query("update GraphInfo g set g.isDeleted = 1 where g.itemId = :id and g.itemType = com.lease.model.enums.ItemType.APARTMENT")
    void deleteApartmentGraphByItemId(Long id);

    @Modifying
    @Query("update GraphInfo g set g.isDeleted = 1 where g.itemId = :id and g.itemType = com.lease.model.enums.ItemType.ROOM")
    void deleteRoomGraphByItemId(Long id);
}
