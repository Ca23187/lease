package com.lease.web.admin.vo.room;

import com.lease.model.enums.ReleaseStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class RoomItemVo {

    private Long id;

    private String roomNumber;

    private BigDecimal rent;

    private Long apartmentId;

    private ReleaseStatus isRelease;

    private Boolean isCheckIn;

    private Date leaseEndDate;

    private ApartmentInfo apartmentInfo;

    @Getter
    @Setter
    public static class ApartmentInfo {
        private Long id;
        private String name;
        private String introduction;
        private Long districtId;
        private String districtName;
        private Long cityId;
        private String cityName;
        private Long provinceId;
        private String provinceName;
        private String addressDetail;
        private String latitude;
        private String longitude;
        private String phone;
        private Integer isRelease;
    }
}
