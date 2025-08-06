package com.lease.web.app.service;

import com.lease.web.app.vo.apartment.ApartmentDetailVo;

/**
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service
 */
public interface ApartmentInfoService {
    ApartmentDetailVo getDetailById(Long id);
}
