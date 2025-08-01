package com.lease.web.admin.vo.room;

import com.lease.model.enums.ReleaseStatus;

import java.math.BigDecimal;

public interface RoomInfoVo {
    Long getId();
    String getRoomNumber();
    BigDecimal getRent();
    ReleaseStatus getIsRelease();
    Long getApartmentId();
}
