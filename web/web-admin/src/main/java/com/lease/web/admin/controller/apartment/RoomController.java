package com.lease.web.admin.controller.apartment;


import com.lease.common.result.Result;
import com.lease.model.enums.ReleaseStatus;
import com.lease.web.admin.dto.room.RoomSubmitDto;
import com.lease.web.admin.service.RoomInfoService;
import com.lease.web.admin.vo.room.RoomDetailVo;
import com.lease.web.admin.vo.room.RoomInfoVo;
import com.lease.web.admin.vo.room.RoomItemVo;
import com.lease.web.admin.dto.room.RoomQueryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "房间信息管理")
@RestController
@RequestMapping("/admin/room")
public class RoomController {

    private final RoomInfoService roomInfoService;

    @Autowired
    public RoomController(RoomInfoService roomInfoService) {
        this.roomInfoService = roomInfoService;
    }

    @Operation(summary = "保存或更新房间信息")
    @PostMapping("saveOrUpdate")
    public Result<Void> saveOrUpdate(@RequestBody RoomSubmitDto dto) {
        roomInfoService.saveOrUpdate(dto);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询房间列表")
    @GetMapping("pageItem")
    public Result<Page<RoomItemVo>> pageItem(
            @RequestParam int page,
            @RequestParam int size,
            RoomQueryDto queryDto) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<RoomItemVo> result = roomInfoService.pageRooms(queryDto, pageable);
        return Result.ok(result);
    }

    @Operation(summary = "根据id获取房间详细信息")
    @GetMapping("getDetailById")
    public Result<RoomDetailVo> getDetailById(@RequestParam Long id) {
        return Result.ok(roomInfoService.getDetailById(id));
    }

    @Operation(summary = "根据id删除房间信息")
    @DeleteMapping("removeById")
    public Result<Void> removeById(@RequestParam Long id) {
        roomInfoService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id修改房间发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result<Void> updateReleaseStatusById(Long id, ReleaseStatus status) {
        roomInfoService.updateReleaseStatusById(id, status);
        return Result.ok();
    }

    @GetMapping("listBasicByApartmentId")
    @Operation(summary = "根据公寓id查询房间列表")
    public Result<List<RoomInfoVo>> listBasicByApartmentId(Long id) {
        return Result.ok(roomInfoService.listBasicByApartmentId(id));
    }

}


















