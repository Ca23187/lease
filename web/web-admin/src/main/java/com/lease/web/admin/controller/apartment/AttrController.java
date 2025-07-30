package com.lease.web.admin.controller.apartment;


import com.lease.common.result.Result;
import com.lease.model.entity.AttrKey;
import com.lease.web.admin.dto.attr.AttrValueDto;
import com.lease.web.admin.mapper.attr.AttrValueMapper;
import com.lease.web.admin.service.AttrKeyService;
import com.lease.web.admin.service.AttrValueService;
import com.lease.web.admin.vo.attr.AttrKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "房间属性管理")
@RestController
@RequestMapping("/admin/attr")
public class AttrController {

    private final AttrKeyService attrKeyService;
    private final AttrValueService attrValueService;
    private final AttrValueMapper attrValueMapper;

    @Autowired
    public AttrController(AttrKeyService attrKeyService, AttrValueService attrValueService,
                          AttrValueMapper attrValueMapper) {
        this.attrKeyService = attrKeyService;
        this.attrValueService = attrValueService;
        this.attrValueMapper = attrValueMapper;
    }

    @Operation(summary = "新增或更新属性名称")
    @PostMapping("key/saveOrUpdate")
    public Result<Void> saveOrUpdateAttrKey(@RequestBody AttrKey attrKey) {
        attrKeyService.saveOrUpdate(attrKey);
        return Result.ok();
    }

    @Operation(summary = "新增或更新属性值")
    @PostMapping("value/saveOrUpdate")
    public Result<Void> saveOrUpdateAttrValue(@RequestBody AttrValueDto attrValueDto) {
        attrValueService.saveOrUpdate(attrValueMapper.toEntity(attrValueDto));
        return Result.ok();
    }


    @Operation(summary = "查询全部属性名称和属性值列表")
    @GetMapping("list")
    public Result<List<AttrKeyVo>> listAttrInfo() {
        return Result.ok(attrKeyService.listAttrInfo());
    }

    @Operation(summary = "根据id删除属性名称")
    @DeleteMapping("key/deleteById")
    public Result<Void> removeAttrKeyById(@RequestParam Long attrKeyId) {
        attrKeyService.removeById(attrKeyId);
        return Result.ok();
    }

    @Operation(summary = "根据id删除属性值")
    @DeleteMapping("value/deleteById")
    public Result<Void> removeAttrValueById(@RequestParam Long id) {
        attrValueService.removeById(id);
        return Result.ok();
    }

}
