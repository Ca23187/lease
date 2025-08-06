package com.lease.web.app.service.impl;

import com.lease.model.entity.ApartmentInfo;
import com.lease.web.app.mapper.apartment.ApartmentInfoMapper;
import com.lease.web.app.repository.ApartmentInfoRepository;
import com.lease.web.app.repository.GraphInfoRepository;
import com.lease.web.app.repository.RoomInfoRepository;
import com.lease.web.app.service.ApartmentInfoService;
import com.lease.web.app.vo.apartment.ApartmentDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service实现
 */
@Service
public class ApartmentInfoServiceImpl implements ApartmentInfoService {

    private final ApartmentInfoRepository apartmentInfoRepository;
    private final RoomInfoRepository roomInfoRepository;
    private final GraphInfoRepository graphInfoRepository;
    private final ApartmentInfoMapper apartmentInfoMapper;

    @Autowired
    public ApartmentInfoServiceImpl(ApartmentInfoRepository apartmentInfoRepository, RoomInfoRepository roomInfoRepository, GraphInfoRepository graphInfoRepository,
                                    ApartmentInfoMapper apartmentInfoMapper) {
        this.apartmentInfoRepository = apartmentInfoRepository;
        this.roomInfoRepository = roomInfoRepository;
        this.graphInfoRepository = graphInfoRepository;
        this.apartmentInfoMapper = apartmentInfoMapper;
    }

    @Override
    public ApartmentDetailVo getDetailById(Long id) {
        ApartmentInfo apartmentInfo = apartmentInfoRepository.findWithFacilityListById(id);
        ApartmentDetailVo vo = apartmentInfoMapper.toDetailVo(apartmentInfo);
        vo.setMinRent(roomInfoRepository.findMinRentByApartmentInfo_Id(id));
        vo.setGraphVoList(graphInfoRepository.findApartmentGraphVoByApartmentId(id));
        return vo;
    }
}




