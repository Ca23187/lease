package com.lease.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lease.model.enums.ReleaseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "房间信息表")
@Setter
@Getter
@Entity
@SQLDelete(sql = "UPDATE room_info SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted = 0")
@Table(name = "room_info")
public class RoomInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "房间号")
    @Column(name = "room_number")
    private String roomNumber;

    @Schema(description = "租金（元/月）")
    @Column(name = "rent")
    private BigDecimal rent;

    @Schema(description = "是否发布")
    @Column(name = "is_release")
    @Convert(converter = ReleaseStatus.ReleaseStatusToIntegerConverter.class)
    private ReleaseStatus isRelease;

    @JsonIgnore
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private ApartmentInfo apartmentInfo;

    @Schema(description = "配套设施列表")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "room_facility",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id")
    )
    private List<FacilityInfo> facilityInfoList;

    @Schema(description = "标签列表")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "room_label",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private List<LabelInfo> labelInfoList;

    @JsonIgnore
    @Schema(description = "基本属性列表")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "room_attr_value",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "attr_value_id")
    )
    private List<AttrValue> attrValueList;

    @Schema(description = "支付方式列表")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "room_payment_type",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_type_id")
    )
    private List<PaymentType> paymentTypeList;

    @Schema(description = "基本属性列表")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "room_lease_term",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "lease_term_id")
    )
    private List<LeaseTerm> leaseTermList;

    @JsonIgnore
    @Schema(description="房间图片列表")
    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    @Where(clause = "item_type = 2")
    private List<GraphInfo> graphInfoList;
}