package com.lease.web.app.service;

import com.lease.model.entity.LeaseTerm;

import java.util.List;

/**
* @description 针对表【lease_term(租期)】的数据库操作Service
*/
public interface LeaseTermService {
    List<LeaseTerm> findByRoomId(Long id);
}
