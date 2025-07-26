package com.lease.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lease.model.enums.LeaseSourceType;
import com.lease.model.enums.LeaseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.Date;

@Schema(description = "租约信息表")
@Setter
@Getter
@Entity
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

    @Schema(description = "签约公寓id")
    @Column(name = "apartment_id")
    private Long apartmentId;

    @Schema(description = "签约房间id")
    @Column(name = "room_id")
    private Long roomId;

    @Schema(description = "租约开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "lease_start_date")
    private Date leaseStartDate;

    @Schema(description = "租约结束日期")
    @Column(name = "lease_end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date leaseEndDate;

    @Schema(description = "租期id")
    @Column(name = "lease_term_id")
    private Long leaseTermId;

    @Schema(description = "租金（元/月）")
    @Column(name = "rent")
    private BigDecimal rent;

    @Schema(description = "押金（元）")
    @Column(name = "deposit")
    private BigDecimal deposit;

    @Schema(description = "支付类型id")
    @Column(name = "payment_type_id")
    private Long paymentTypeId;

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