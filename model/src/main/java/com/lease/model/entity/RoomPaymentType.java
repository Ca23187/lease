package com.lease.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;

@Schema(description = "房间&支付方式关联表")
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE room_payment_type SET is_deleted = 1, update_time = now() WHERE id = ?")
@Where(clause = "is_deleted = 0")
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