package com.lease.web.admin.controller.lease;


import com.lease.common.result.Result;
import com.lease.model.entity.LeaseAgreement;
import com.lease.model.enums.LeaseStatus;
import com.lease.web.admin.dto.lease.AgreementQueryDto;
import com.lease.web.admin.service.LeaseAgreementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@Tag(name = "租约管理")
@RestController
@RequestMapping("/admin/agreement")
public class LeaseAgreementController {

    private final LeaseAgreementService service;

    public LeaseAgreementController(LeaseAgreementService service) {
        this.service = service;
    }

    @Operation(summary = "保存或修改租约信息")
    @PostMapping("saveOrUpdate")
    public Result<Void> saveOrUpdate(@RequestBody LeaseAgreement leaseAgreement) {
        leaseAgreement.getApartmentInfo().setId(leaseAgreement.getApartmentId());
        leaseAgreement.getRoomInfo().setId(leaseAgreement.getRoomId());
        leaseAgreement.getPaymentType().setId(leaseAgreement.getPaymentTypeId());
        leaseAgreement.getLeaseTerm().setId(leaseAgreement.getLeaseTermId());
        service.saveOrUpdate(leaseAgreement);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询租约列表")
    @GetMapping("page")
    public Result<Page<LeaseAgreement>> page(@RequestParam int current, @RequestParam int size, AgreementQueryDto queryDto) {
        Pageable pageable = PageRequest.of(current - 1, size);
        Page<LeaseAgreement> result = service.pageViewAgreements(queryDto, pageable);
        return Result.ok(result);
    }

    @Operation(summary = "根据id查询租约信息")
    @GetMapping(name = "getById")
    public Result<LeaseAgreement> getById(@RequestParam Long id) {
        return Result.ok(service.findById(id));
    }

    @Operation(summary = "根据id删除租约信息")
    @DeleteMapping("removeById")
    public Result<Void> removeById(@RequestParam Long id) {
        service.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id更新租约状态")
    @PostMapping("updateStatusById")
    public Result<Void> updateStatusById(@RequestParam Long id, @RequestParam LeaseStatus status) {
        service.updateStatusById(id, status);
        return Result.ok();
    }

}

