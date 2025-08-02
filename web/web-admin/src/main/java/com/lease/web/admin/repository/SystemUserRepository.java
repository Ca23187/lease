package com.lease.web.admin.repository;

import com.lease.model.entity.SystemUser;
import com.lease.model.enums.BaseStatus;
import com.lease.web.admin.vo.system.user.SystemUserInfoVo;
import com.lease.web.admin.vo.system.user.SystemUserItemVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
    @Query("""
    SELECT new com.lease.web.admin.vo.system.user.SystemUserItemVo(
        su.id,
        su.username,
        su.name,
        su.type,
        su.phone,
        su.avatarUrl,
        su.additionalInfo,
        sp.id,
        sp.name,
        su.status
    )
    FROM SystemUser su
    LEFT JOIN su.systemPost sp
    WHERE (:name IS NULL OR su.name LIKE CONCAT('%', :name, '%'))
      AND (:phone IS NULL OR su.phone LIKE CONCAT('%', :phone, '%'))
    """)
    Page<SystemUserItemVo> getPage(String name, String phone, Pageable pageable);

    @Query("""
    SELECT new com.lease.web.admin.vo.system.user.SystemUserItemVo(
        su.id,
        su.username,
        su.name,
        su.type,
        su.phone,
        su.avatarUrl,
        su.additionalInfo,
        sp.id,
        sp.name,
        su.status
    )
    FROM SystemUser su
    LEFT JOIN su.systemPost sp
    WHERE su.id = :id
    """)
    SystemUserItemVo getById(Long id);

    Integer countByUsername(String username);

    @Modifying
    @Query("update SystemUser s set s.status = :status, s.updateTime = current_timestamp where s.id = :id")
    void updateStatusById(BaseStatus status, Long id);

    SystemUser findByUsername(String username);

    @Query("""
    select new com.lease.web.admin.vo.system.user.SystemUserInfoVo(
        u.name,
        u.avatarUrl
    )
    from SystemUser u
    where u.id = :userId
    """)
    SystemUserInfoVo findSystemUserInfoVoById(Long userId);
}
