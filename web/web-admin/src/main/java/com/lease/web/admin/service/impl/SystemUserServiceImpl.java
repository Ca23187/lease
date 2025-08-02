package com.lease.web.admin.service.impl;

import com.lease.model.entity.SystemUser;
import com.lease.model.enums.BaseStatus;
import com.lease.web.admin.dto.system.user.SystemUserQueryDto;
import com.lease.web.admin.repository.SystemUserRepository;
import com.lease.web.admin.service.SystemUserService;
import com.lease.web.admin.vo.system.user.SystemUserItemVo;
import jakarta.persistence.Transient;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description 针对表【system_user(员工信息表)】的数据库操作Service实现
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserRepository repository;

    @Autowired
    public SystemUserServiceImpl(SystemUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<SystemUserItemVo> getPage(Pageable pageable, SystemUserQueryDto queryDto) {
        return repository.getPage(
                queryDto.getName(),
                queryDto.getPhone(),
                pageable
        );
    }

    @Override
    public SystemUserItemVo getById(Long id) {
        return repository.getById(id);
    }

    @Transient
    @Override
    public void saveOrUpdate(SystemUser systemUser) {
        if (systemUser.getPassword() != null) {
            systemUser.setPassword(DigestUtils.md5Hex(systemUser.getPassword()));
        } else {
            SystemUser dbUser = repository.findById(systemUser.getId())
                .orElseThrow(() -> new RuntimeException("用户不存在"));
            systemUser.setPassword(dbUser.getPassword());
        }
        repository.save(systemUser);
    }

    @Override
    public Integer countByUsername(String username) {
        return repository.countByUsername(username);
    }

    @Transactional
    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateStatusById(Long id, BaseStatus status) {
        repository.updateStatusById(status, id);
    }
}




