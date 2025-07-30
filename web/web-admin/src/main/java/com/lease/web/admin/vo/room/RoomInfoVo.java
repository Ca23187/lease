package com.lease.web.admin.vo.room;

import com.lease.model.enums.ReleaseStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RoomInfoVo {
    private Long id;

    private String roomNumber;

    private BigDecimal rent;

    private ReleaseStatus isRelease;

}
