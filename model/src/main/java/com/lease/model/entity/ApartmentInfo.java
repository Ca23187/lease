package com.lease.model.entity;

import com.lease.model.enums.ReleaseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Schema(description = "公寓信息表")
@Setter
@Getter
@Entity
@Table(name = "apartment_info")
public class ApartmentInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "公寓名称")
    @Column(name = "name")
    private String name;

    @Schema(description = "公寓介绍")
    @Column(name = "introduction")
    private String introduction;

    @Schema(description = "所处区域id")
    @Column(name = "district_id")
    private Long districtId;

    @Schema(description = "所处区域名称")
    @Column(name = "district_name")
    private String districtName;

    @Schema(description = "所处城市id")
    @Column(name = "city_id")
    private Long cityId;

    @Schema(description = "所处城市名称")
    @Column(name = "city_name")
    private String cityName;

    @Schema(description = "所处省份id")
    @Column(name = "province_id")
    private Long provinceId;

    @Schema(description = "所处区域名称")
    @Column(name = "province_name")
    private String provinceName;

    @Schema(description = "详细地址")
    @Column(name = "address_detail")
    private String addressDetail;

    @Schema(description = "经度")
    @Column(name = "latitude")
    private String latitude;

    @Schema(description = "纬度")
    @Column(name = "longitude")
    private String longitude;

    @Schema(description = "公寓前台电话")
    @Column(name = "phone")
    private String phone;

    @Schema(description = "是否发布")
    @Column(name = "is_release")
    @Convert(converter = ReleaseStatus.ReleaseStatusToIntegerConverter.class)
    private ReleaseStatus isRelease;

}