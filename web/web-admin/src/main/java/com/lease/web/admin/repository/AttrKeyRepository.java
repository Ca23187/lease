package com.lease.web.admin.repository;

import com.lease.model.entity.AttrKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttrKeyRepository extends JpaRepository<AttrKey, Long> {

    @Query("SELECT ak FROM AttrKey ak LEFT JOIN FETCH ak.attrValueList")
    List<AttrKey> findAllWithValues();
}
