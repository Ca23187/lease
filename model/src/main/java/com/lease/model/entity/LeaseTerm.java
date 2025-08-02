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

/**
 * @TableName lease_term
 */
@Schema(description = "租期信息")
@Setter
@Getter
@Entity
@SQLDelete(sql = "UPDATE lease_term SET is_deleted = 1, update_time = now() WHERE id = ?")
@Where(clause = "is_deleted = 0")
@Table(name = "lease_term")
public class LeaseTerm extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "租期月数")
    @Column(name = "month_count")
    private Integer monthCount;

    @Schema(description = "租期单位:月")
    @Column(name = "unit")
    private String unit;
}