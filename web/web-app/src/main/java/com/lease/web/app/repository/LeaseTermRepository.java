package com.lease.web.app.repository;

import com.lease.model.entity.LeaseTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaseTermRepository extends JpaRepository<LeaseTerm, Long> {

    @Query("""
    select lt
    from LeaseTerm lt
    where lt.id in (
        select rlt.leaseTermId
        from RoomLeaseTerm rlt
        where rlt.roomId = :id
    )
    """)
    List<LeaseTerm> findByRoomId(Long id);
}
