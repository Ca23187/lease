package com.lease.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.List;

/*
1. 首先必须指定序列化顺序：DistrictInfo -> CityInfo -> ProvinceInfo
对于三个以上实体类的序列化顺序设置，需要互相两两设置 @JsonManagedReference 和 @JsonBackReference
且需要设置value来进行成对的区分
2. 指定顺序后，为了避免 DistrictInfo 显示 CityInfo，CityInfo 显示 ProvinceInfo
需要将所有的 @ManyToOne 属性打上 @JsonIgnore 以中断进一步的序列化
 */
@Schema(description = "省份信息表")
@Setter
@Getter
@Entity
@Table(name = "province_info")
public class ProvinceInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "省份名称")
    @Column(name = "name")
    private String name;

    @JsonBackReference(value = "province-city")
    @OneToMany(mappedBy = "provinceInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CityInfo> cityInfoList;

}