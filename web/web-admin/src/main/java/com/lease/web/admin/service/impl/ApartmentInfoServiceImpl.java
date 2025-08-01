package com.lease.web.admin.service.impl;

import com.lease.common.exception.LeaseException;
import com.lease.common.result.ResultCodeEnum;
import com.lease.model.entity.ApartmentInfo;
import com.lease.model.entity.GraphInfo;
import com.lease.model.enums.ItemType;
import com.lease.model.enums.ReleaseStatus;
import com.lease.web.admin.dto.apartment.ApartmentQueryDto;
import com.lease.web.admin.dto.apartment.ApartmentSubmitDto;
import com.lease.web.admin.mapper.apartment.ApartmentInfoMapper;
import com.lease.web.admin.repository.*;
import com.lease.web.admin.service.ApartmentInfoService;
import com.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.lease.web.admin.vo.apartment.ApartmentInfoVo;
import com.lease.web.admin.vo.apartment.ApartmentItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service实现
 */
@Service
public class ApartmentInfoServiceImpl implements ApartmentInfoService {

    private final ApartmentInfoRepository apartmentInfoRepository;
    private final FacilityInfoRepository facilityInfoRepository;
    private final LabelInfoRepository labelInfoRepository;
    private final FeeValueRepository feeValueRepository;
    private final ApartmentInfoMapper apartmentInfoMapper;
    private final RoomInfoRepository roomInfoRepository;
    private final GraphInfoRepository graphInfoRepository;

    @Autowired
    public ApartmentInfoServiceImpl(ApartmentInfoRepository apartmentInfoRepository, FacilityInfoRepository facilityInfoRepository, LabelInfoRepository labelInfoRepository, FeeValueRepository feeValueRepository, ApartmentInfoMapper apartmentInfoMapper,
                                    RoomInfoRepository roomInfoRepository, GraphInfoRepository graphInfoRepository) {
        this.apartmentInfoRepository = apartmentInfoRepository;
        this.facilityInfoRepository = facilityInfoRepository;
        this.labelInfoRepository = labelInfoRepository;
        this.feeValueRepository = feeValueRepository;
        this.apartmentInfoMapper = apartmentInfoMapper;
        this.roomInfoRepository = roomInfoRepository;
        this.graphInfoRepository = graphInfoRepository;
    }

    @Override
    @Transactional
    public void saveOrUpdate(ApartmentSubmitDto dto) {
        ApartmentInfo apartmentInfo;
        if (dto.getId() != null) {
            apartmentInfo = apartmentInfoRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("未找到指定的公寓信息"));
            graphInfoRepository.deleteApartmentGraphByItemId(apartmentInfo.getId());
            apartmentInfo.getFacilityInfoList().clear();
            apartmentInfo.getFeeValueList().clear();
            apartmentInfo.getLabelInfoList().clear();
        } else {
            apartmentInfo = new ApartmentInfo();
        }
        apartmentInfoMapper.updateFromDto(dto, apartmentInfo);

        apartmentInfo.setFacilityInfoList(facilityInfoRepository.findAllById(dto.getFacilityInfoIds()));
        apartmentInfo.setLabelInfoList(labelInfoRepository.findAllById(dto.getLabelIds()));
        apartmentInfo.setFeeValueList(feeValueRepository.findAllById(dto.getFeeValueIds()));
        ApartmentInfo save = apartmentInfoRepository.save(apartmentInfo);
        graphInfoRepository.saveAll(dto.getGraphVoList().stream()
                .map(graphVo -> {
                    GraphInfo graph = new GraphInfo();
                    graph.setName(graphVo.getName());
                    graph.setUrl(graphVo.getUrl());
                    graph.setItemType(ItemType.APARTMENT);
                    graph.setItemId(save.getId());
                    return graph;
                }).toList());
    }

    @Override
    public Page<ApartmentItemVo> pageApartments(ApartmentQueryDto queryDto, Pageable pageable) {
        return apartmentInfoRepository.pageApartmentItems(
                queryDto.getProvinceId(),
                queryDto.getCityId(),
                queryDto.getDistrictId(),
                pageable
        );
    }

    @Override
    public ApartmentDetailVo getDetailById(Long id) {
        ApartmentInfo apartmentInfo = apartmentInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("未找到指定的公寓信息"));
        ApartmentDetailVo apartmentDetailVo = apartmentInfoMapper.toDetailVo(apartmentInfo);
        apartmentDetailVo.setFeeValueVoList(feeValueRepository.findAllFeeValueVoByApartmentId(id));
        apartmentDetailVo.setGraphVoList(graphInfoRepository.findAllGraphVoById(id, ItemType.APARTMENT));
        return apartmentDetailVo;
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        if (roomInfoRepository.countByApartmentInfoId(id) != 0) {
            throw new LeaseException(ResultCodeEnum.ADMIN_APARTMENT_DELETE_ERROR);
        }
        apartmentInfoRepository.deleteById(id);
        graphInfoRepository.deleteApartmentGraphByItemId(id);
    }

    @Override
    @Transactional
    public void updateReleaseStatusById(Long id, ReleaseStatus status) {
        apartmentInfoRepository.updateReleaseStatusById(id, status);
    }

    @Override
    public List<ApartmentInfoVo> listInfoByDistrictId(Long id) {
        return apartmentInfoRepository.findByDistrictId(id);
    }

}




