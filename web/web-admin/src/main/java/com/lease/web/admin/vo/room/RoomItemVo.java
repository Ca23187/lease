package com.lease.web.admin.vo.room;

import com.lease.model.enums.ReleaseStatus;

import java.math.BigDecimal;
import java.util.Date;

public interface RoomItemVo {

    Long getId();

    String getRoomNumber();
    BigDecimal getRent();
    Long getApartmentId();
    ReleaseStatus getIsRelease();

    Boolean getIsCheckIn();
    Date getLeaseEndDate();

    ApartmentInfoProjection getApartmentInfo();

    interface ApartmentInfoProjection {
        Long getId();
        String getName();
        String getIntroduction();
        Long getDistrictId();
        String getDistrictName();
        Long getCityId();
        String getCityName();
        Long getProvinceId();
        String getProvinceName();
        String getAddressDetail();
        String getLatitude();
        String getLongitude();
        String getPhone();
        Integer getIsRelease();
    }
}
