package com.lease.web.admin.repository;

import com.lease.model.entity.UserInfo;
import com.lease.model.enums.BaseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{

    @Query("""
    SELECT u FROM UserInfo u
        WHERE (:phone IS NULL OR u.phone LIKE %:phone%)
            AND (:status IS NULL OR u.status = :status)
    """)
    Page<UserInfo> findByQuery(String phone, BaseStatus status, Pageable pageable);

    @Query("update UserInfo u set u.status = :status, u.updateTime = current_timestamp where u.id = :id")
    @Modifying
    void updateStatusById(BaseStatus status, Long id);
}
