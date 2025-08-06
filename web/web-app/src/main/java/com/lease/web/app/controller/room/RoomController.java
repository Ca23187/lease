package com.lease.web.app.controller.room;


import com.lease.common.result.Result;
import com.lease.web.app.dto.room.RoomQueryDto;
import com.lease.web.app.service.RoomInfoService;
import com.lease.web.app.vo.room.RoomDetailVo;
import com.lease.web.app.vo.room.RoomItemVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "房间信息")
@RestController
@RequestMapping("/app/room")
public class RoomController {

    private final RoomInfoService service;

    @Autowired
    public RoomController(RoomInfoService service) {
        this.service = service;
    }

    @Operation(summary = "分页查询房间列表")
    @GetMapping("pageItem")
    public Result<Page<RoomItemVo>> pageItem(@RequestParam int current, @RequestParam int size, RoomQueryDto queryDto) {
        Sort sort = Sort.by(Sort.Direction.fromString(queryDto.getOrderType()), "rent");
        Pageable pageable = PageRequest.of(current - 1, size, sort);
        return Result.ok(service.pageItem(queryDto, pageable));
    }

    @Operation(summary = "根据id获取房间的详细信息")
    @GetMapping("getDetailById")
    public Result<RoomDetailVo> getDetailById(@RequestParam Long id) {
        return Result.ok(service.getDetailById(id));
    }

    @Operation(summary = "根据公寓id分页查询房间列表")
    @GetMapping("pageItemByApartmentId")
    public Result<Page<RoomItemVo>> pageItemByApartmentId(@RequestParam int current, @RequestParam int size, @RequestParam Long id) {
        Pageable pageable = PageRequest.of(current - 1, size);
        return Result.ok(service.pageItemByApartmentId(id, pageable));
    }
}
