package com.lease.model.entity;

import com.lease.model.enums.AppointmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.Date;

@Schema(description = "预约看房信息表")
@Setter
@Getter
@Entity
@Table(name = "view_appointment")
public class ViewAppointment extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    @Column(name = "user_id")
    private Long userId;

    @Schema(description = "用户姓名")
    @Column(name = "name")
    private String name;

    @Schema(description = "用户手机号码")
    @Column(name = "phone")
    private String phone;

    @Schema(description = "公寓id")
    @Column(name = "apartment_id")
    private Long apartmentId;

    @Schema(description = "预约时间")
    @Column(name = "appointment_time")
    private Date appointmentTime;

    @Schema(description = "备注信息")
    @Column(name = "additional_info")
    private String additionalInfo;

    @Schema(description = "预约状态")
    @Column(name = "appointment_status")
    @Convert(converter = AppointmentStatus.AppointmentStatusToIntegerConverter.class)
    private AppointmentStatus appointmentStatus;
}