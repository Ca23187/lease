package com.lease.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;

@Schema(description = "杂项费用值表")
@Setter
@Getter
@Entity
@SQLDelete(sql = "UPDATE fee_value SET is_deleted = 1, update_time = now() WHERE id = ?")
@Where(clause = "is_deleted = 0")
@Table(name = "fee_value")
public class FeeValue extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "费用value")
    @Column(name = "name")
    private String name;

    @Schema(description = "收费单位")
    @Column(name = "unit")
    private String unit;

    @JsonBackReference  // 阻止OneToMany和ManyToOne同时使用导致的递归序列化
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_key_id")
    private FeeKey feeKey;

    @Transient
    private Long feeKeyId;
}