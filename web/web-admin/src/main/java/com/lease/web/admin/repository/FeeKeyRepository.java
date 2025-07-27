package com.lease.web.admin.repository;

import com.lease.model.entity.FeeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeeKeyRepository extends JpaRepository<FeeKey, Long> {
    @Query("SELECT fk FROM FeeKey fk LEFT JOIN FETCH fk.feeValueList")
    List<FeeKey> findAllWithValues();
}
