package com.lease.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;

@Schema(description = "支付方式表")
@Setter
@Getter
@Entity
@Table(name = "payment_type")
@SQLDelete(sql = "UPDATE payment_type SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted = 0")
public class PaymentType extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "付款方式名称")
    @Column(name = "name")
    private String name;

    @Schema(description = "每次支付租期数")
    @Column(name = "pay_month_count")
    private String payMonthCount;

    @Schema(description = "付费说明")
    @Column(name = "additional_info")
    private String additionalInfo;

}