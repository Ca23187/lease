package com.lease.web.admin.repository;

import com.lease.model.entity.LeaseTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaseTermRepository extends JpaRepository<LeaseTerm, Long> {

}
