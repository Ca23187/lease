package com.lease.web.admin.vo.apartment;

import com.lease.model.enums.ReleaseStatus;

public interface ApartmentInfoVo {
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
    ReleaseStatus getIsRelease();
}
