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
import java.math.BigDecimal;

@Schema(description = "房间信息表")
@Setter
@Getter
@Entity
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

    @Schema(description = "所属公寓id")
    @Column(name = "apartment_id")
    private Long apartmentId;

    @Schema(description = "是否发布")
    @Column(name = "is_release")
    @Convert(converter = ReleaseStatus.ReleaseStatusToIntegerConverter.class)
    private ReleaseStatus isRelease;

}