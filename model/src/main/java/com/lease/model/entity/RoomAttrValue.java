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

@Schema(description = "房间&基本属性值关联表")
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "room_attr_value")
public class RoomAttrValue extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "房间id")
    @Column(name = "room_id")
    private Long roomId;

    @Schema(description = "属性值id")
    @Column(name = "attr_value_id")
    private Long attrValueId;
}