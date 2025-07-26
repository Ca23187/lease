package com.lease.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Schema(description = "杂项费用名称表")
@Setter
@Getter
@Entity
@Table(name = "fee_key")
public class FeeKey extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "付款项key")
    @Column(name = "name")
    private String name;

}