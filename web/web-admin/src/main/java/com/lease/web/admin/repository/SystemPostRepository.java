package com.lease.web.admin.repository;

import com.lease.model.entity.SystemPost;
import com.lease.model.enums.BaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SystemPostRepository extends JpaRepository<SystemPost, Long> {
    @Query("update SystemPost s set s.status = :status, s.updateTime = current_timestamp where s.id = :id")
    @Modifying
    void updateStatusById(BaseStatus status, Long id);
}
