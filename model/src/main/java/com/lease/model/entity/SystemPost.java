package com.lease.model.entity;

import com.lease.model.enums.BaseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;

/**
 * 岗位信息表
 *
 * @TableName system_post
 */
@Setter
@Getter
@Entity
@SQLDelete(sql = "UPDATE system_post SET is_deleted = 1, update_time = now() WHERE id = ?")
@Where(clause = "is_deleted = 0")
@Table(name = "system_post")
public class SystemPost extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "岗位编码")
    @Column(name = "code")
    private String postCode;

    @Schema(description = "岗位名称")
    @Column(name = "name")
    private String name;

    @Schema(description = "岗位描述信息")
    @Column(name = "description")
    private String description;

    @Schema(description = "岗位状态")
    @Column(name = "status")
    @Convert(converter = BaseStatus.BaseStatusToIntegerConverter.class)
    private BaseStatus status;

}