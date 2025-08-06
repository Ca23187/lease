package com.lease.web.app.controller.appointment;


import com.lease.common.login.LoginUserHolder;
import com.lease.common.result.Result;
import com.lease.model.entity.ViewAppointment;
import com.lease.web.app.service.ViewAppointmentService;
import com.lease.web.app.vo.appointment.AppointmentDetailVo;
import com.lease.web.app.vo.appointment.AppointmentItemVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "看房预约信息")
@RestController
@RequestMapping("/app/appointment")
public class ViewAppointmentController {

    private final ViewAppointmentService service;

    @Autowired
    public ViewAppointmentController(ViewAppointmentService service) {
        this.service = service;
    }

    @Operation(summary = "保存或更新看房预约")
    @PostMapping("/saveOrUpdate")
    public Result<Void> saveOrUpdate(@RequestBody ViewAppointment viewAppointment) {
        viewAppointment.getApartmentInfo().setId(viewAppointment.getId());
        service.saveOrUpdate(viewAppointment);
        return Result.ok();
    }

    @Operation(summary = "查询个人预约看房列表")
    @GetMapping("listItem")
    public Result<List<AppointmentItemVo>> listItem() {
        Long userId = LoginUserHolder.getLoginUser().getUserId();
        return Result.ok(service.listItem(userId));
    }

    @GetMapping("getDetailById")
    @Operation(summary = "根据ID查询预约详情信息")
    public Result<AppointmentDetailVo> getDetailById(Long id) {
        return Result.ok(service.getDetailById(id));
    }

}

