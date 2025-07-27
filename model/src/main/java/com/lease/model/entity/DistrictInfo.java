package com.lease.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Schema(description = "地区信息表")
@Setter
@Getter
@Entity
@Table(name = "district_info")
public class DistrictInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "区域名称")
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference(value = "city-district")
    @JsonIgnore
    @JoinColumn(name = "city_id")
    private CityInfo cityInfo;

}