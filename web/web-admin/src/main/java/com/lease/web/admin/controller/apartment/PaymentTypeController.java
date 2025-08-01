package com.lease.web.admin.controller.apartment;

import com.lease.common.result.Result;
import com.lease.model.entity.PaymentType;
import com.lease.web.admin.service.PaymentTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "支付方式管理")
@RequestMapping("/admin/payment")
@RestController
public class PaymentTypeController {

    private final PaymentTypeService service;

    @Autowired
    public PaymentTypeController(PaymentTypeService service) {
        this.service = service;
    }


    @Operation(summary = "查询全部支付方式列表")
    @GetMapping("list")
    public Result<List<PaymentType>> listPaymentType() {
        return Result.ok(service.list());
    }

    @Operation(summary = "保存或更新支付方式")
    @PostMapping("saveOrUpdate")
    public Result<Void> saveOrUpdatePaymentType(@RequestBody PaymentType paymentType) {
        service.saveOrUpdate(paymentType);
        return Result.ok();
    }

    @Operation(summary = "根据ID删除支付方式")
    @DeleteMapping("deleteById")
    public Result<Void> deletePaymentById(@RequestParam Long id) {
        service.removeById(id);
        return Result.ok();
    }

}















