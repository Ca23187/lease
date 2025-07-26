package com.lease.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

@Schema(description = "房间&支付方式关联表")
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "room_payment_type")
public class RoomPaymentType extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "房间id")
    @Column(name = "room_id")
    private Long roomId;

    @Schema(description = "支付类型id")
    @Column(name = "payment_type_id")
    private Long paymentTypeId;

}