package com.lease.web.admin.controller.system;

import com.lease.common.result.Result;
import com.lease.model.entity.SystemPost;
import com.lease.model.enums.BaseStatus;
import com.lease.web.admin.service.SystemPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Tag(name = "后台用户岗位管理")
@RequestMapping("/admin/system/post")
public class SystemPostController {

    private final SystemPostService service;

    @Autowired
    public SystemPostController(SystemPostService service) {
        this.service = service;
    }


    @Operation(summary = "分页获取岗位信息")
    @GetMapping("page")
    private Result<Page<SystemPost>> page(@RequestParam int current, @RequestParam int size) {
        Pageable pageable = PageRequest.of(current - 1, size);
        Page<SystemPost> page = service.getPage(pageable);
        return Result.ok(page);
    }

    @Operation(summary = "保存或更新岗位信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SystemPost systemPost) {
        service.saveOrUpdate(systemPost);
        return Result.ok();
    }

    @DeleteMapping("deleteById")
    @Operation(summary = "根据id删除岗位")
    public Result<Void> removeById(@RequestParam Long id) {
        service.removeById(id);
        return Result.ok();
    }

    @GetMapping("getById")
    @Operation(summary = "根据id获取岗位信息")
    public Result<SystemPost> getById(@RequestParam Long id) {
        return Result.ok(service.getById(id));
    }

    @Operation(summary = "获取全部岗位列表")
    @GetMapping("list")
    public Result<List<SystemPost>> list() {
        return Result.ok(service.list());
    }

    @Operation(summary = "根据岗位id修改状态")
    @PostMapping("updateStatusByPostId")
    public Result<Void> updateStatusByPostId(@RequestParam Long id, @RequestParam BaseStatus status) {
        service.updateStatusByPostId(id, status);
        return Result.ok();
    }
}
