package com.lease.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Schema(description = "地区信息表")
@Getter
@Setter
@Entity
@Table(name = "district_info")
public class DistrictInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "区域名称")
    @Column(name = "name")
    private String name;

    @Schema(description = "所属城市id")
    @Column(name = "city_id")
    private Long cityId;

}