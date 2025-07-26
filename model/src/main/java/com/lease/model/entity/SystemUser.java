package com.lease.model.entity;

import com.lease.model.enums.BaseStatus;
import com.lease.model.enums.SystemUserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Schema(description = "员工信息")
@Setter
@Getter
@Entity
@Table(name = "system_user")
public class SystemUser extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户名")
    @Column(name = "username")
    private String username;

    @Schema(description = "密码")
    @Column(name = "password")
    private String password;

    @Schema(description = "姓名")
    @Column(name = "name")
    private String name;

    @Schema(description = "用户类型")
    @Column(name = "type")
    @Convert(converter = SystemUserType.SystemUserTypeToIntegerConverter.class)
    private SystemUserType type;

    @Schema(description = "手机号码")
    @Column(name = "phone")
    private String phone;

    @Schema(description = "头像地址")
    @Column(name = "avatar_url")
    private String avatarUrl;

    @Schema(description = "备注信息")
    @Column(name = "additional_info")
    private String additionalInfo;

    @Schema(description = "岗位id")
    @Column(name = "post_id")
    private Long postId;

    @Schema(description = "账号状态")
    @Column(name = "status")
    @Convert(converter = BaseStatus.BaseStatusToIntegerConverter.class)
    private BaseStatus status;

}