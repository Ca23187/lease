package com.lease.web.admin.vo.apartment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApartmentInfoVo {
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
