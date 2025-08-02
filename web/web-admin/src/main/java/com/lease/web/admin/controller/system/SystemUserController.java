package com.lease.web.admin.controller.system;


import com.lease.common.result.Result;
import com.lease.model.entity.SystemPost;
import com.lease.model.entity.SystemUser;
import com.lease.model.enums.BaseStatus;
import com.lease.web.admin.service.SystemUserService;
import com.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.lease.web.admin.dto.system.user.SystemUserQueryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@Tag(name = "后台用户信息管理")
@RestController
@RequestMapping("/admin/system/user")
public class SystemUserController {

    private final SystemUserService service;

    @Autowired
    public SystemUserController(SystemUserService service) {
        this.service = service;
    }

    @Operation(summary = "根据条件分页查询后台用户列表")
    @GetMapping("page")
    public Result<Page<SystemUserItemVo>> page(@RequestParam int current, @RequestParam int size, SystemUserQueryDto queryDto) {
        Pageable pageable = PageRequest.of(current - 1, size);
        return Result.ok(service.getPage(pageable, queryDto));
    }

    @Operation(summary = "根据ID查询后台用户信息")
    @GetMapping("getById")
    public Result<SystemUserItemVo> getById(@RequestParam Long id) {
        return Result.ok(service.getById(id));
    }

    @Operation(summary = "保存或更新后台用户信息")
    @PostMapping("saveOrUpdate")
    public Result<Void> saveOrUpdate(@RequestBody SystemUser systemUser) {
        SystemPost systemPost = new SystemPost();
        systemPost.setId(systemUser.getPostId());
        systemUser.setSystemPost(systemPost);
        service.saveOrUpdate(systemUser);
        return Result.ok();
    }

    @Operation(summary = "判断后台用户名是否可用")
    @GetMapping("isUserNameAvailable")
    public Result<Boolean> isUsernameExists(@RequestParam String username) {
        return Result.ok(service.countByUsername(username) == 0);
    }

    @DeleteMapping("deleteById")
    @Operation(summary = "根据ID删除后台用户信息")
    public Result<Void> removeById(@RequestParam Long id) {
        service.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "根据ID修改后台用户状态")
    @PostMapping("updateStatusByUserId")
    public Result<Void> updateStatusByUserId(@RequestParam Long id, @RequestParam BaseStatus status) {
        service.updateStatusById(id, status);
        return Result.ok();
    }
}
