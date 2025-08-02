package com.lease.web.admin.vo.system.user;

import com.lease.model.entity.SystemUser;
import com.lease.model.enums.BaseStatus;
import com.lease.model.enums.SystemUserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Schema(description = "后台管理系统用户基本信息实体")
public class SystemUserItemVo extends SystemUser {

    @Schema(description = "岗位名称")
    private String postName;

    public SystemUserItemVo(Long id,
                            String username,
                            String name,
                            SystemUserType type,
                            String phone,
                            String avatarUrl,
                            String additionalInfo,
                            Long postId,
                            String postName,
                            BaseStatus status) {
        super.setId(id);
        super.setUsername(username);
        super.setName(name);
        super.setType(type);
        super.setPhone(phone);
        super.setAvatarUrl(avatarUrl);
        super.setAdditionalInfo(additionalInfo);
        super.setPostId(postId);
        this.postName = postName;
        super.setStatus(status);
    }

}
