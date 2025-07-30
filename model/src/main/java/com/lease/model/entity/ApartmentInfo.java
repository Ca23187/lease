package com.lease.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lease.model.enums.ReleaseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.util.List;

@Schema(description = "公寓信息表")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE apartment_info SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted = 0")
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

    @Schema(description = "配套设施列表")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "apartment_facility",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id")
    )
    private List<FacilityInfo> facilityInfoList;

    @Schema(description = "标签列表")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "apartment_label",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private List<LabelInfo> labelInfoList;

    @JsonIgnore
    @Schema(description = "费用项列表")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "apartment_fee_value",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "fee_value_id")
    )
    private List<FeeValue> feeValueList;

    @JsonIgnore
    @Schema(description="公寓图片列表")
    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    @Where(clause = "item_type = 1")
    private List<GraphInfo> graphInfoList;

    @JsonIgnore
    @Schema(description="公寓房间列表")
    @JsonManagedReference
    @OneToMany(mappedBy = "apartmentInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomInfo> roomInfoList;
}