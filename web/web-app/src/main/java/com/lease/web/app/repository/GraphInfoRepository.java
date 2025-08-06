package com.lease.web.app.repository;

import com.lease.model.entity.GraphInfo;
import com.lease.web.app.vo.graph.ApartmentGraphVo;
import com.lease.web.app.vo.graph.GraphVo;
import com.lease.web.app.vo.graph.RoomGraphVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GraphInfoRepository extends JpaRepository<GraphInfo, Long> {

    @Query("""
    select new com.lease.web.app.vo.graph.RoomGraphVo(gi.itemId, gi.name, gi.url)
    from GraphInfo gi
    where gi.itemType = com.lease.model.enums.ItemType.ROOM
        and gi.itemId in :roomIds
    """)
    List<RoomGraphVo> findRoomGraphVoByRoomIds(List<Long> roomIds);

    @Query("""
    select new com.lease.web.app.vo.graph.GraphVo(
        gi.name,
        gi.url
    )
    from GraphInfo gi
    where gi.itemType = com.lease.model.enums.ItemType.ROOM
        and gi.itemId = :id
    """)
    List<GraphVo> findRoomGraphVoByRoomId(Long id);

    @Query("""
    select new com.lease.web.app.vo.graph.GraphVo(
        gi.name,
        gi.url
    )
    from GraphInfo gi
    where gi.itemType = com.lease.model.enums.ItemType.APARTMENT
        and gi.itemId = :id
    """)
    List<GraphVo> findApartmentGraphVoByApartmentId(Long id);

    @Query("""
    select new com.lease.web.app.vo.graph.ApartmentGraphVo(gi.itemId, gi.name, gi.url)
    from GraphInfo gi
    where gi.itemType = com.lease.model.enums.ItemType.APARTMENT
        and gi.itemId in :ids
    """)
    List<ApartmentGraphVo> findApartmentGraphVoByApartmentIds(List<Long> ids);
}
