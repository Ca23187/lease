package com.lease.web.app.repository;

import com.lease.model.entity.BrowsingHistory;
import com.lease.web.app.vo.history.HistoryItemVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BrowsingHistoryRepository extends JpaRepository<BrowsingHistory, Long> {

    @Query("""
    select new com.lease.web.app.vo.history.HistoryItemVo(
        bh.id,
        bh.userId,
        bh.roomId,
        bh.browseTime,
        ri.roomNumber,
        ri.rent,
        ai.name,
        ai.provinceName,
        ai.cityName,
        ai.districtName
    )
    from BrowsingHistory bh
    left join RoomInfo ri on bh.roomId = ri.id
    left join ApartmentInfo ai on ai.id = ri.apartmentInfo.id
    order by bh.browseTime desc
    """)
    Page<HistoryItemVo> pageItemByUserId(Long userId, Pageable pageable);

    boolean existsByUserIdAndRoomId(Long userId, Long roomId);

    @Modifying
    @Query("""
    update BrowsingHistory
    set browseTime = current_timestamp
    where userId = :userId and roomId = :roomId
    """)
    void updateBrowseTimeByUserIdAndRoomId(Long userId, Long roomId);
}
