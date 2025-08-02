package com.lease.web.admin.service.impl;

import com.lease.model.entity.SystemPost;
import com.lease.model.enums.BaseStatus;
import com.lease.web.admin.repository.SystemPostRepository;
import com.lease.web.admin.service.SystemPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @description 针对表【system_post(岗位信息表)】的数据库操作Service实现
*/
@Service
public class SystemPostServiceImpl implements SystemPostService{

    private final SystemPostRepository repository;

    @Autowired
    public SystemPostServiceImpl(SystemPostRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<SystemPost> getPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void saveOrUpdate(SystemPost systemPost) {
        repository.save(systemPost);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public SystemPost getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("未找到指定的岗位id"));
    }

    @Override
    public List<SystemPost> list() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void updateStatusByPostId(Long id, BaseStatus status) {
        repository.updateStatusById(status, id);
    }
}




