package com.lease.web.admin.controller.apartment;


import com.lease.common.result.Result;
import com.lease.model.enums.ReleaseStatus;
import com.lease.web.admin.dto.apartment.ApartmentQueryDto;
import com.lease.web.admin.dto.apartment.ApartmentSubmitDto;
import com.lease.web.admin.service.ApartmentInfoService;
import com.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.lease.web.admin.vo.apartment.ApartmentInfoVo;
import com.lease.web.admin.vo.apartment.ApartmentItemVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "公寓信息管理")
@RestController
@RequestMapping("/admin/apartment")
public class ApartmentController {

    private final ApartmentInfoService apartmentInfoService;

    @Autowired
    public ApartmentController(ApartmentInfoService apartmentInfoService) {
        this.apartmentInfoService = apartmentInfoService;
    }

    @Operation(summary = "保存或更新公寓信息")
    @PostMapping("saveOrUpdate")
    public Result<Void> saveOrUpdate(@RequestBody ApartmentSubmitDto dto) {
        apartmentInfoService.saveOrUpdate(dto);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询公寓列表")
    @GetMapping("/pageItem")
    public Result<Page<ApartmentItemVo>> pageItem(
            @RequestParam int current,
            @RequestParam int size,
            ApartmentQueryDto queryDto) {
        Pageable pageable = PageRequest.of(current - 1, size);
        Page<ApartmentItemVo> result = apartmentInfoService.pageApartments(queryDto, pageable);
        return Result.ok(result);
    }


    @Operation(summary = "根据id获取公寓详细信息")
    @GetMapping("getDetailById")
    public Result<ApartmentDetailVo> getDetailById(@RequestParam Long id) {
        return Result.ok(apartmentInfoService.getDetailById(id));
    }

    @Operation(summary = "根据id删除公寓信息")
    @DeleteMapping("removeById")
    public Result<Void> removeById(@RequestParam Long id) {
        apartmentInfoService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id修改公寓发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result<Void> updateReleaseStatusById(@RequestParam Long id, @RequestParam ReleaseStatus status) {
        apartmentInfoService.updateReleaseStatusById(id, status);
        return Result.ok();
    }

    @Operation(summary = "根据区县id查询公寓信息列表")
    @GetMapping("listInfoByDistrictId")
    public Result<List<ApartmentInfoVo>> listInfoByDistrictId(@RequestParam Long id) {
        return Result.ok(apartmentInfoService.listInfoByDistrictId(id));
    }
}














