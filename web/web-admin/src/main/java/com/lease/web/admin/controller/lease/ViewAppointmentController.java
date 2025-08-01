package com.lease.web.admin.controller.lease;


import com.lease.common.result.Result;
import com.lease.model.entity.ViewAppointment;
import com.lease.model.enums.AppointmentStatus;
import com.lease.web.admin.dto.appointment.AppointmentQueryDto;
import com.lease.web.admin.service.ViewAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@Tag(name = "预约看房管理")
@RequestMapping("/admin/appointment")
@RestController
public class ViewAppointmentController {

    private final ViewAppointmentService service;

    @Autowired
    public ViewAppointmentController(ViewAppointmentService service) {
        this.service = service;
    }

    @Operation(summary = "分页查询预约信息")
    @GetMapping("page")
    public Result<Page<ViewAppointment>> page(@RequestParam int current, @RequestParam int size, AppointmentQueryDto queryDto) {
        Pageable pageable = PageRequest.of(current - 1, size);
        Page<ViewAppointment> result = service.pageViewAppointments(queryDto, pageable);
        return Result.ok(result);
    }

    @Operation(summary = "根据id更新预约状态")
    @PostMapping("updateStatusById")
    public Result<Void> updateStatusById(@RequestParam Long id, @RequestParam AppointmentStatus status) {
        service.updateStatusById(id, status);
        return Result.ok();
    }

}
