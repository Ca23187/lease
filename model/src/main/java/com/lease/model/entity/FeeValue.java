package com.lease.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Schema(description = "杂项费用值表")
@Setter
@Getter
@Entity
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

    @Schema(description = "费用所对的fee_key编码")
    @Column(name = "fee_key_id")
    private Long feeKeyId;

}