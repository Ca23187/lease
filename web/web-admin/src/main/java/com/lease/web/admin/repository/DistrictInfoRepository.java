package com.lease.web.admin.repository;

import com.lease.model.entity.DistrictInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictInfoRepository extends JpaRepository<DistrictInfo, Long> {
    List<DistrictInfo> findAllByCityId(Long cityId);
}
