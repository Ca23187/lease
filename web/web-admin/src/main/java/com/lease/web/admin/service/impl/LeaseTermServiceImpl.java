package com.lease.web.admin.service.impl;

import com.lease.model.entity.LeaseTerm;
import com.lease.web.admin.repository.LeaseTermRepository;
import com.lease.web.admin.service.LeaseTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description 针对表【lease_term(租期)】的数据库操作Service实现
*/
@Service
public class LeaseTermServiceImpl implements LeaseTermService {

    private final LeaseTermRepository repository;

    @Autowired
    public LeaseTermServiceImpl(LeaseTermRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LeaseTerm> list() {
        return repository.findAll();
    }

    @Override
    public void saveOrUpdate(LeaseTerm leaseTerm) {
        repository.save(leaseTerm);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}




