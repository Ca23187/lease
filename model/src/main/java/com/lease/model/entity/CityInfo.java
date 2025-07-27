package com.lease.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.List;

@Schema(description = "城市信息表")
@Setter
@Getter
@Entity
@Table(name = "city_info")
public class CityInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "城市名称")
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference(value = "province-city")
    @JsonIgnore
    @JoinColumn(name = "province_id")
    private ProvinceInfo provinceInfo;

    @JsonBackReference(value = "city-district")
    @OneToMany(mappedBy = "cityInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DistrictInfo> districtInfoList;
}