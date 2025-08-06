package com.lease.web.app.service.impl;

import com.lease.common.constant.RedisConstant;
import com.lease.common.login.LoginUserHolder;
import com.lease.model.entity.LabelInfo;
import com.lease.model.entity.RoomInfo;
import com.lease.web.app.dto.room.RoomQueryDto;
import com.lease.web.app.mapper.room.RoomInfoMapper;
import com.lease.web.app.repository.*;
import com.lease.web.app.service.BrowsingHistoryService;
import com.lease.web.app.service.RoomInfoService;
import com.lease.web.app.vo.graph.GraphVo;
import com.lease.web.app.vo.graph.RoomGraphVo;
import com.lease.web.app.vo.room.RoomDetailVo;
import com.lease.web.app.vo.room.RoomItemVo;
import com.lease.web.app.vo.room.RoomLabelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description 针对表【room_info(房间信息表)】的数据库操作Service实现
 */
@Service
@Slf4j
public class RoomInfoServiceImpl implements RoomInfoService {
    private final RoomInfoMapper roomInfoMapper;
    private final RoomInfoRepository roomInfoRepository;
    private final LabelInfoRepository labelInfoRepository;
    private final GraphInfoRepository graphInfoRepository;
    private final AttrValueRepository attrValueRepository;
    private final FeeValueRepository feeValueRepository;
    private final BrowsingHistoryService browsingHistoryService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RoomInfoServiceImpl(RoomInfoRepository roomInfoRepository,
                               RoomInfoMapper roomInfoMapper, LabelInfoRepository labelInfoRepository, GraphInfoRepository graphInfoRepository, AttrValueRepository attrValueRepository, FeeValueRepository feeValueRepository, BrowsingHistoryService browsingHistoryService, RedisTemplate<String, Object> redisTemplate) {
        this.roomInfoRepository = roomInfoRepository;
        this.roomInfoMapper = roomInfoMapper;
        this.labelInfoRepository = labelInfoRepository;
        this.graphInfoRepository = graphInfoRepository;
        this.attrValueRepository = attrValueRepository;
        this.feeValueRepository = feeValueRepository;
        this.browsingHistoryService = browsingHistoryService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Page<RoomItemVo> pageItem(RoomQueryDto queryDto, Pageable pageable) {
        Page<RoomInfo> roomInfoPage = roomInfoRepository.pageItem(
                queryDto.getProvinceId(),
                queryDto.getCityId(),
                queryDto.getDistrictId(),
                queryDto.getMinRent(),
                queryDto.getMaxRent(),
                queryDto.getPaymentTypeId(),
                pageable
        );

        Page<RoomItemVo> roomItemVoPage = roomInfoPage.map(roomInfoMapper::toVo);

        List<Long> roomIds = roomInfoPage.stream().map(RoomInfo::getId).toList();

        Map<Long, List<LabelInfo>> labelMap = labelInfoRepository.findRoomLabelVoByRoomIds(roomIds)
                .stream()
                .collect(Collectors.groupingBy(
                        RoomLabelVo::getRoomId,
                        Collectors.mapping(vo -> new LabelInfo(vo.getLabelId(), vo.getType(), vo.getLabelName()), Collectors.toList())
                ));
        Map<Long, List<GraphVo>> graphMap = graphInfoRepository.findRoomGraphVoByRoomIds(roomIds)
                .stream()
                .collect(Collectors.groupingBy(
                        RoomGraphVo::getRoomId,
                        Collectors.mapping(vo -> new GraphVo(vo.getName(), vo.getUrl()), Collectors.toList())
                ));

        for (RoomItemVo r : roomItemVoPage) {
            r.setLabelInfoList(labelMap.getOrDefault(r.getId(), List.of()));
            r.setGraphVoList(graphMap.getOrDefault(r.getId(), List.of()));
        }
        return roomItemVoPage;
    }

    @Override
    public Page<RoomItemVo> pageItemByApartmentId(Long id, Pageable pageable) {
        Page<RoomInfo> roomInfoPage = roomInfoRepository.pageItemByApartmentId(id, pageable);

        Page<RoomItemVo> roomItemVoPage = roomInfoPage.map(roomInfoMapper::toVo);

        List<Long> roomIds = roomInfoPage.stream().map(RoomInfo::getId).toList();

        Map<Long, List<LabelInfo>> labelMap = labelInfoRepository.findRoomLabelVoByRoomIds(roomIds)
                .stream()
                .collect(Collectors.groupingBy(
                        RoomLabelVo::getRoomId,
                        Collectors.mapping(vo -> new LabelInfo(vo.getLabelId(), vo.getType(), vo.getLabelName()), Collectors.toList())
                ));
        Map<Long, List<GraphVo>> graphMap = graphInfoRepository.findRoomGraphVoByRoomIds(roomIds)
                .stream()
                .collect(Collectors.groupingBy(
                        RoomGraphVo::getRoomId,
                        Collectors.mapping(vo -> new GraphVo(vo.getName(), vo.getUrl()), Collectors.toList())
                ));

        for (RoomItemVo r : roomItemVoPage) {
            r.setLabelInfoList(labelMap.getOrDefault(r.getId(), List.of()));
            r.setGraphVoList(graphMap.getOrDefault(r.getId(), List.of()));
        }

        return roomItemVoPage;
    }

    @Override
    public RoomDetailVo getDetailById(Long id) {
        String key = RedisConstant.APP_ROOM_PREFIX + id;
        RoomDetailVo vo = (RoomDetailVo) redisTemplate.opsForValue().get(key);
        if (vo == null) {
            RoomInfo roomInfo = roomInfoRepository.getDetailById(id);
            if (roomInfo == null) return null;

            vo = roomInfoMapper.toDetailVo(roomInfo);
            vo.getApartmentItemVo().setMinRent(roomInfoRepository.findMinRentByApartmentInfo_Id(vo.getApartmentId()));
            vo.getApartmentItemVo().setGraphVoList(graphInfoRepository.findApartmentGraphVoByApartmentId(vo.getApartmentId()));
            vo.setFeeValueVoList(feeValueRepository.findAllFeeValueVoByApartmentId(vo.getApartmentId()));
            vo.setAttrValueVoList(attrValueRepository.findAllAttrValueVoByRoomId(id));
            vo.setGraphVoList(graphInfoRepository.findRoomGraphVoByRoomId(id));

            redisTemplate.opsForValue().set(key, vo);
        }

        // 保存浏览历史
        browsingHistoryService.saveHistory(LoginUserHolder.getLoginUser().getUserId(), id);

        return vo;
    }
}




