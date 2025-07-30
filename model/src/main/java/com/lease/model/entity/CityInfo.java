package com.lease.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Schema(description = "城市信息表")
@Getter
@Setter
@Entity
@Table(name = "city_info")
public class CityInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "城市名称")
    @Column(name = "name")
    private String name;

    @Schema(description = "所属省份id")
    @Column(name = "province_id")
    private Long provinceId;

}