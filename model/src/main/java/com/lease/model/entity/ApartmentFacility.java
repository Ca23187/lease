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

@Schema(description = "公寓&配套关系")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "apartment_facility")
public class ApartmentFacility extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "公寓id")
    @Column(name = "apartment_id")
    private Long apartmentId;

    @Schema(description = "设施id")
    @Column(name = "facility_id")
    private Long facilityId;

}