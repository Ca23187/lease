package com.lease.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lease.model.enums.LeaseSourceType;
import com.lease.model.enums.LeaseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.Date;

@Schema(description = "租约信息表")
@Setter
@Getter
@Entity
@SQLDelete(sql = "UPDATE lease_agreement SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted = 0")
@Table(name = "lease_agreement")
public class LeaseAgreement extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "承租人手机号码")
    @Column(name = "phone")
    private String phone;

    @Schema(description = "承租人姓名")
    @Column(name = "name")
    private String name;

    @Schema(description = "承租人身份证号码")
    @Column(name = "identification_number")
    private String identificationNumber;

    @Transient
    private Long apartmentId;

    @Schema(description = "签约公寓id")
    @JsonIgnoreProperties({"labelInfoList", "facilityInfoList"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private ApartmentInfo apartmentInfo;

    @Transient
    private Long roomId;

    @Schema(description = "签约房间")
    @JsonIgnoreProperties({"labelInfoList", "facilityInfoList", "paymentTypeList", "leaseTermList", "apartmentInfo"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private RoomInfo roomInfo;

    @Schema(description = "租约开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "lease_start_date")
    private Date leaseStartDate;

    @Schema(description = "租约结束日期")
    @Column(name = "lease_end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date leaseEndDate;

    @Transient
    private Long leaseTermId;

    @Schema(description = "租期id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lease_term_id")
    private LeaseTerm leaseTerm;

    @Schema(description = "租金（元/月）")
    @Column(name = "rent")
    private BigDecimal rent;

    @Schema(description = "押金（元）")
    @Column(name = "deposit")
    private BigDecimal deposit;

    @Transient
    private Long paymentTypeId;

    @Schema(description = "支付类型")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;

    @Schema(description = "租约状态")
    @Column(name = "status")
    @Convert(converter = LeaseStatus.LeaseStatusToIntegerConverter.class)
    private LeaseStatus status;

    @Schema(description = "租约来源")
    @Column(name = "source_type")
    @Convert(converter = LeaseSourceType.LeaseSourceTypeToIntegerConverter.class)
    private LeaseSourceType sourceType;

    @Schema(description = "备注信息")
    @Column(name = "additional_info")
    private String additionalInfo;

}