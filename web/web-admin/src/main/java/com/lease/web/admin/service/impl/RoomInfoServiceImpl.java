package com.lease.web.admin.service.impl;

import com.lease.model.entity.GraphInfo;
import com.lease.model.entity.RoomInfo;
import com.lease.model.enums.ItemType;
import com.lease.web.admin.controller.projection.room.RoomItemVoProjection;
import com.lease.web.admin.dto.room.RoomSubmitDto;
import com.lease.web.admin.mapper.apartment.ApartmentItemVoProjectionMapper;
import com.lease.web.admin.mapper.attr.AttrValueWithNameMapper;
import com.lease.web.admin.mapper.graph.GraphInfoMapper;
import com.lease.web.admin.mapper.room.RoomInfoMapper;
import com.lease.web.admin.mapper.room.RoomItemVoProjectionMapper;
import com.lease.web.admin.repository.*;
import com.lease.web.admin.service.RoomInfoService;
import com.lease.web.admin.vo.room.RoomDetailVo;
import com.lease.web.admin.vo.room.RoomInfoVo;
import com.lease.web.admin.vo.room.RoomItemVo;
import com.lease.web.admin.vo.room.RoomQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description 针对表【room_info(房间信息表)】的数据库操作Service实现
 */
@Service
public class RoomInfoServiceImpl implements RoomInfoService {

    private final RoomInfoRepository roomInfoRepository;
    private final RoomInfoMapper roomInfoMapper;
    private final AttrValueRepository attrValueRepository;
    private final FacilityInfoRepository facilityInfoRepository;
    private final LabelInfoRepository labelInfoRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final LeaseTermRepository leaseTermRepository;
    private final RoomItemVoProjectionMapper roomItemVoProjectionMapper;
    private final AttrValueWithNameMapper attrValueWithNameMapper;
    private final GraphInfoMapper graphInfoMapper;
    private final GraphInfoRepository graphInfoRepository;

    @Autowired
    public RoomInfoServiceImpl(RoomInfoRepository roomInfoRepository, RoomInfoMapper roomInfoMapper, AttrValueRepository attrValueRepository, FacilityInfoRepository facilityInfoRepository, LabelInfoRepository labelInfoRepository, PaymentTypeRepository paymentTypeRepository, LeaseTermRepository leaseTermRepository, ApartmentItemVoProjectionMapper apartmentItemVoProjectionMapper, RoomItemVoProjectionMapper roomItemVoProjectionMapper, AttrValueWithNameMapper attrValueWithNameMapper, GraphInfoMapper graphInfoMapper, GraphInfoRepository graphInfoRepository) {
        this.roomInfoRepository = roomInfoRepository;
        this.roomInfoMapper = roomInfoMapper;
        this.attrValueRepository = attrValueRepository;
        this.facilityInfoRepository = facilityInfoRepository;
        this.labelInfoRepository = labelInfoRepository;
        this.paymentTypeRepository = paymentTypeRepository;
        this.leaseTermRepository = leaseTermRepository;
        this.roomItemVoProjectionMapper = roomItemVoProjectionMapper;
        this.attrValueWithNameMapper = attrValueWithNameMapper;
        this.graphInfoMapper = graphInfoMapper;
        this.graphInfoRepository = graphInfoRepository;
    }

    @Override
    @Transactional
    public void saveOrUpdate(RoomSubmitDto dto) {
        RoomInfo roomInfo;
        if (dto.getId() != null) {
            roomInfo = roomInfoRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("未找到指定的房间信息"));
            graphInfoRepository.deleteRoomGraphByItemId(roomInfo.getId());
            roomInfo.getFacilityInfoList().clear();
            roomInfo.getLabelInfoList().clear();
            roomInfo.getAttrValueList().clear();
            roomInfo.getPaymentTypeList().clear();
            roomInfo.getLeaseTermList().clear();
        } else {
            roomInfo = new RoomInfo();
        }
        roomInfoMapper.updateFromDto(dto, roomInfo);

        roomInfo.setFacilityInfoList(facilityInfoRepository.findAllById(dto.getFacilityInfoIds()));
        roomInfo.setLabelInfoList(labelInfoRepository.findAllById(dto.getLabelInfoIds()));
        roomInfo.setAttrValueList(attrValueRepository.findAllById(dto.getAttrValueIds()));
        roomInfo.setPaymentTypeList(paymentTypeRepository.findAllById(dto.getPaymentTypeIds()));
        roomInfo.setLeaseTermList(leaseTermRepository.findAllById(dto.getLeaseTermIds()));
        RoomInfo save = roomInfoRepository.save(roomInfo);
        graphInfoRepository.saveAll(dto.getGraphVoList().stream()
                .map(graphVo -> {
                    GraphInfo graph = new GraphInfo();
                    graph.setName(graphVo.getName());
                    graph.setUrl(graphVo.getUrl());
                    graph.setItemType(ItemType.ROOM);
                    graph.setItemId(save.getId());
                    return graph;
                }).toList());
    }

    @Override
    public Page<RoomItemVo> pageRooms(RoomQueryVo queryVo, Pageable pageable) {
        Page<RoomItemVoProjection> pageProjection = roomInfoRepository.pageRoomItemProjections(queryVo, pageable);
        List<RoomItemVo> voList = roomItemVoProjectionMapper.toVoList(pageProjection.getContent());
        return new PageImpl<>(voList, pageable, pageProjection.getTotalElements());
    }

    @Override
    public RoomDetailVo getDetailById(Long id) {
        RoomInfo roomInfo = roomInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("未找到指定的房间信息"));
        RoomDetailVo roomDetailVo = roomInfoMapper.toDetailVo(roomInfo);
        roomDetailVo.setAttrValueVoList(attrValueWithNameMapper.toVoListWithName(roomInfo.getAttrValueList()));
        roomDetailVo.setGraphVoList(graphInfoMapper.toVoList(roomInfo.getGraphInfoList()));
        return roomDetailVo;
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        roomInfoRepository.deleteById(id);
        graphInfoRepository.deleteRoomGraphByItemId(id);
    }

    @Override
    public List<RoomInfoVo> listBasicByApartmentId(Long id) {
        return roomInfoMapper.toVoList(roomInfoRepository.findByApartmentInfo_Id(id));
    }
}




