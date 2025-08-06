package com.lease.web.app.controller.agreement;

import com.lease.common.result.Result;
import com.lease.model.entity.LeaseAgreement;
import com.lease.model.enums.LeaseStatus;
import com.lease.web.app.service.LeaseAgreementService;
import com.lease.web.app.vo.agreement.AgreementDetailVo;
import com.lease.web.app.vo.agreement.AgreementItemVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/agreement")
@Tag(name = "租约信息")
public class LeaseAgreementController {

    private final LeaseAgreementService service;

    @Autowired
    public LeaseAgreementController(LeaseAgreementService service) {
        this.service = service;
    }

    @Operation(summary = "获取个人租约基本信息列表")
    @GetMapping("listItem")
    public Result<List<AgreementItemVo>> listItem() {
        return Result.ok(service.listItem());
    }

    @Operation(summary = "根据id获取租约详细信息")
    @GetMapping("getDetailById")
    public Result<AgreementDetailVo> getDetailById(@RequestParam Long id) {
        return Result.ok(service.getDetailById(id));
    }

    @Operation(summary = "根据id更新租约状态", description = "用于确认租约和提前退租")
    @PostMapping("updateStatusById")
    public Result<Void> updateStatusById(@RequestParam Long id, @RequestParam LeaseStatus leaseStatus) {
        service.updateStatusByValue(id, leaseStatus);
        return Result.ok();
    }

    @Operation(summary = "保存或更新租约", description = "用于续约")
    @PostMapping("saveOrUpdate")
    public Result<Void> saveOrUpdate(@RequestBody LeaseAgreement leaseAgreement) {
        leaseAgreement.getApartmentInfo().setId(leaseAgreement.getApartmentId());
        leaseAgreement.getPaymentType().setId(leaseAgreement.getPaymentTypeId());
        leaseAgreement.getRoomInfo().setId(leaseAgreement.getRoomId());
        leaseAgreement.getLeaseTerm().setId(leaseAgreement.getLeaseTermId());
        service.saveOrUpdate(leaseAgreement);
        return Result.ok();
    }

}
