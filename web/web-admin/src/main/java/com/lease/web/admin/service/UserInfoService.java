package com.lease.web.admin.service;

import com.lease.model.entity.UserInfo;
import com.lease.model.enums.BaseStatus;
import com.lease.web.admin.dto.user.UserInfoQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
* @description 针对表【user_info(用户信息表)】的数据库操作Service
*/
public interface UserInfoService {

    Page<UserInfo> pageUserInfos(UserInfoQueryDto queryDto, Pageable pageable);

    void updateStatusById(Long id, BaseStatus status);
}
