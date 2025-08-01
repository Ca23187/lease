package com.lease.web.admin.controller.apartment;


import com.lease.common.result.Result;
import com.lease.model.entity.FacilityInfo;
import com.lease.model.enums.ItemType;
import com.lease.web.admin.service.FacilityInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "配套管理")
@RestController
@RequestMapping("/admin/facility")
public class FacilityController {

    private final FacilityInfoService service;

    @Autowired
    public FacilityController(FacilityInfoService service) {
        this.service = service;
    }

    @Operation(summary = "[根据类型]查询配套信息列表")
    @GetMapping("list")
    public Result<List<FacilityInfo>> listFacility(@RequestParam(required = false) ItemType type) {
        return Result.ok(service.list(type));
    }

    @Operation(summary = "新增或修改配套信息")
    @PostMapping("saveOrUpdate")
    public Result<Void> saveOrUpdate(@RequestBody FacilityInfo facilityInfo) {
        service.saveOrUpload(facilityInfo);
        return Result.ok();
    }

    @Operation(summary = "根据id删除配套信息")
    @DeleteMapping("deleteById")
    public Result<Void> removeFacilityById(@RequestParam Long id) {
        service.removeById(id);
        return Result.ok();
    }

}
