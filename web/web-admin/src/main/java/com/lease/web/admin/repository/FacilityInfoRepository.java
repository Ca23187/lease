package com.lease.web.admin.repository;

import com.lease.model.entity.FacilityInfo;
import com.lease.model.enums.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityInfoRepository extends JpaRepository<FacilityInfo, Long> {
    List<FacilityInfo> findByType(ItemType type);
}
