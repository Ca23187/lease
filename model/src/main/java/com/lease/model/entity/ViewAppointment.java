package com.lease.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lease.model.enums.AppointmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.util.Date;

@Schema(description = "预约看房信息表")
@Setter
@Getter
@Entity
@SQLDelete(sql = "UPDATE view_appointment SET is_deleted = 1, update_time = now() WHERE id = ?")
@Where(clause = "is_deleted = 0")
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

    @Schema(description = "预约时间")
    @Column(name = "appointment_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date appointmentTime;

    @Schema(description = "备注信息")
    @Column(name = "additional_info")
    private String additionalInfo;

    @Schema(description = "预约状态")
    @Column(name = "appointment_status")
    @Convert(converter = AppointmentStatus.AppointmentStatusToIntegerConverter.class)
    private AppointmentStatus appointmentStatus;

    @Transient
    private Long apartmentId;

    @JsonIgnoreProperties({"labelInfoList", "facilityInfoList"})
    @ManyToOne
    @Schema(description = "公寓id")
    @JoinColumn(name = "apartment_id")
    private ApartmentInfo apartmentInfo;
}