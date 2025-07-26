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

@Schema(description = "房间&配套关联表")
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "room_facility")
public class RoomFacility extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "房间id")
    @Column(name = "room_id")
    private Long roomId;

    @Schema(description = "房间设施id")
    @Column(name = "facility_id")
    private Long facilityId;

}