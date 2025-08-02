package com.lease.web.admin.controller.user;


import com.lease.common.result.Result;
import com.lease.model.entity.UserInfo;
import com.lease.model.enums.BaseStatus;
import com.lease.web.admin.service.UserInfoService;
import com.lease.web.admin.dto.user.UserInfoQueryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户信息管理")
@RestController
@RequestMapping("/admin/user")
public class UserInfoController {

    private final UserInfoService service;

    @Autowired
    public UserInfoController(UserInfoService service) {
        this.service = service;
    }

    @Operation(summary = "分页查询用户信息")
    @GetMapping("page")
    public Result<Page<UserInfo>> pageUserInfo(@RequestParam int current, @RequestParam int size, UserInfoQueryDto queryDto) {
        Pageable pageable = PageRequest.of(current - 1, size);
        Page<UserInfo> result = service.pageUserInfos(queryDto, pageable);
        return Result.ok(result);
    }

    @Operation(summary = "根据用户id更新账号状态")
    @PostMapping("updateStatusById")
    public Result<Void> updateStatusById(@RequestParam Long id, @RequestParam BaseStatus status) {
        service.updateStatusById(id, status);
        return Result.ok();
    }
}
