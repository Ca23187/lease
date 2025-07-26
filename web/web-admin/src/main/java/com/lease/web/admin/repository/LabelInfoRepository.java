package com.lease.web.admin.repository;

import com.lease.model.entity.LabelInfo;
import com.lease.model.enums.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelInfoRepository extends JpaRepository<LabelInfo, Long> {
    List<LabelInfo> findByType(ItemType type);
}
