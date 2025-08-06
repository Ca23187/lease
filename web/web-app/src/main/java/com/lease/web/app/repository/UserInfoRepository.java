package com.lease.web.app.repository;

import com.lease.model.entity.UserInfo;
import com.lease.web.app.vo.user.UserInfoVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findByPhone(String phone);

    @Query("""
    select new com.lease.web.app.vo.user.UserInfoVo(
        u.nickname,
        u.avatarUrl
    )
    from UserInfo u
    where u.id = :userId
    """)
    UserInfoVo getUserInfoVoById(Long userId);
}
