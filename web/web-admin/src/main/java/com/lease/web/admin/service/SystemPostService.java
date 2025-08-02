package com.lease.web.admin.service;

import com.lease.model.entity.SystemPost;
import com.lease.model.enums.BaseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
* @description 针对表【system_post(岗位信息表)】的数据库操作Service
*/
public interface SystemPostService {

    Page<SystemPost> getPage(Pageable pageable);

    void saveOrUpdate(SystemPost systemPost);

    void removeById(Long id);

    SystemPost getById(Long id);

    List<SystemPost> list();

    void updateStatusByPostId(Long id, BaseStatus status);
}
