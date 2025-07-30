package com.lease.web.admin.controller.projection.apartment;

public interface ApartmentItemVoProjection {
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
