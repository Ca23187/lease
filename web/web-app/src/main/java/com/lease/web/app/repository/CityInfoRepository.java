package com.lease.web.app.repository;

import com.lease.model.entity.CityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityInfoRepository extends JpaRepository<CityInfo, Long> {
    List<CityInfo> findAllByProvinceId(Long provinceId);
}
