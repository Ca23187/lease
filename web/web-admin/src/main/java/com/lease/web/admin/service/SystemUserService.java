package com.lease.web.admin.service;

import com.lease.model.entity.SystemUser;
import com.lease.model.enums.BaseStatus;
import com.lease.web.admin.dto.system.user.SystemUserQueryDto;
import com.lease.web.admin.vo.system.user.SystemUserItemVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
* @description 针对表【system_user(员工信息表)】的数据库操作Service
*/
public interface SystemUserService {

    Page<SystemUserItemVo> getPage(Pageable pageable, SystemUserQueryDto queryDto);

    SystemUserItemVo getById(Long id);

    void saveOrUpdate(SystemUser systemUser);

    Integer countByUsername(String username);

    void removeById(Long id);

    void updateStatusById(Long id, BaseStatus status);
}
