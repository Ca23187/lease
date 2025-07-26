package com.lease.model.entity;

import com.lease.model.enums.BaseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Schema(description = "用户信息表")
@Setter
@Getter
@Entity
@Table(name = "user_info")
public class UserInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "手机号码（用做登录用户名）")
    @Column(name = "phone")
    private String phone;

    @Schema(description = "密码")
    @Column(name = "password")
    private String password;

    @Schema(description = "头像url")
    @Column(name = "avatar_url")
    private String avatarUrl;

    @Schema(description = "昵称")
    @Column(name = "nickname")
    private String nickname;

    @Schema(description = "账号状态")
    @Column(name = "status")
    @Convert(converter = BaseStatus.BaseStatusToIntegerConverter.class)
    private BaseStatus status;

}