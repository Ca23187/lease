package com.lease.web.admin.controller.apartment;


import com.lease.common.result.Result;
import com.lease.model.entity.FeeKey;
import com.lease.web.admin.dto.fee.FeeValueDto;
import com.lease.web.admin.mapper.fee.FeeValueMapper;
import com.lease.web.admin.service.FeeKeyService;
import com.lease.web.admin.service.FeeValueService;
import com.lease.web.admin.vo.fee.FeeKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "房间杂费管理")
@RestController
@RequestMapping("/admin/fee")
public class FeeController {

    private final FeeKeyService feeKeyService;
    private final FeeValueService feeValueService;
    private final FeeValueMapper feeValueMapper;

    @Autowired
    public FeeController(FeeKeyService feeKeyService, FeeValueService feeValueService,
                         FeeValueMapper feeValueMapper) {
        this.feeKeyService = feeKeyService;
        this.feeValueService = feeValueService;
        this.feeValueMapper = feeValueMapper;
    }

    @Operation(summary = "保存或更新杂费名称")
    @PostMapping("key/saveOrUpdate")
    public Result<Void> saveOrUpdateFeeKey(@RequestBody FeeKey feeKey) {
        feeKeyService.saveOrUpdate(feeKey);
        return Result.ok();
    }

    @Operation(summary = "保存或更新杂费值")
    @PostMapping("value/saveOrUpdate")
    public Result<Void> saveOrUpdateFeeValue(@RequestBody FeeValueDto feeValueDto) {
        feeValueService.saveOrUpdate(feeValueMapper.toEntity(feeValueDto));
        return Result.ok();
    }


    @Operation(summary = "查询全部杂费名称和杂费值列表")
    @GetMapping("list")
    public Result<List<FeeKeyVo>> feeInfoList() {
        return Result.ok(feeKeyService.feeInfoList());
    }

    @Operation(summary = "根据id删除杂费名称")
    @DeleteMapping("key/deleteById")
    public Result<Void> deleteFeeKeyById(@RequestParam Long feeKeyId) {
        feeKeyService.deleteById(feeKeyId);
        return Result.ok();
    }

    @Operation(summary = "根据id删除杂费值")
    @DeleteMapping("value/deleteById")
    public Result<Void> deleteFeeValueById(@RequestParam Long id) {
        feeValueService.deleteById(id);
        return Result.ok();
    }
}
