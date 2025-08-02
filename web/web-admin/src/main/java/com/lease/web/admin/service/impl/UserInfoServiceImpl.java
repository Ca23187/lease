package com.lease.web.admin.service.impl;

import com.lease.model.entity.UserInfo;
import com.lease.model.enums.BaseStatus;
import com.lease.web.admin.dto.user.UserInfoQueryDto;
import com.lease.web.admin.repository.UserInfoRepository;
import com.lease.web.admin.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @description 针对表【user_info(用户信息表)】的数据库操作Service实现
*/
@Service
public class UserInfoServiceImpl implements UserInfoService{

    private final UserInfoRepository repository;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<UserInfo> pageUserInfos(UserInfoQueryDto queryDto, Pageable pageable) {
        return repository.findByQuery(
                queryDto.getPhone(),
                queryDto.getStatus(),
                pageable
        );
    }

    @Override
    @Transactional
    public void updateStatusById(Long id, BaseStatus status) {
        repository.updateStatusById(status, id);
    }
}




