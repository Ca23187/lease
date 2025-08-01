package com.lease.web.admin.vo.apartment;

public interface ApartmentItemVo {
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
    Integer getTotalRoomCount();
    Integer getFreeRoomCount();
}
