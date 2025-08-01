package com.lease.web.admin.service;

import com.lease.model.enums.ReleaseStatus;
import com.lease.web.admin.dto.apartment.ApartmentQueryDto;
import com.lease.web.admin.dto.apartment.ApartmentSubmitDto;
import com.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.lease.web.admin.vo.apartment.ApartmentInfoVo;
import com.lease.web.admin.vo.apartment.ApartmentItemVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
* @description 针对表【apartment_info(公寓信息表)】的数据库操作Service
*/
public interface ApartmentInfoService {

    void saveOrUpdate(ApartmentSubmitDto dto);

    Page<ApartmentItemVo> pageApartments(ApartmentQueryDto queryDto, Pageable pageable);

    ApartmentDetailVo getDetailById(Long id);

    void removeById(Long id);

    void updateReleaseStatusById(Long id, ReleaseStatus status);

    List<ApartmentInfoVo> listInfoByDistrictId(Long id);
}
