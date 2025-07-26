package com.lease.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

@Schema(description = "公寓&杂费关联表")
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "apartment_fee_value")
public class ApartmentFeeValue extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "公寓id")
    @Column(name = "apartment_id")
    private Long apartmentId;

    @Schema(description = "收费项value_id")
    @Column(name = "fee_value_id")
    private Long feeValueId;

}