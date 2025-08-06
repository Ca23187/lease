package com.lease.web.app.service.impl;

import com.lease.model.entity.LeaseAgreement;
import com.lease.model.enums.LeaseStatus;
import com.lease.web.app.mapper.agreement.LeaseAgreementMapper;
import com.lease.web.app.repository.GraphInfoRepository;
import com.lease.web.app.repository.LeaseAgreementRepository;
import com.lease.web.app.service.LeaseAgreementService;
import com.lease.web.app.vo.agreement.AgreementDetailVo;
import com.lease.web.app.vo.agreement.AgreementItemVo;
import com.lease.web.app.vo.graph.GraphVo;
import com.lease.web.app.vo.graph.RoomGraphVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description 针对表【lease_agreement(租约信息表)】的数据库操作Service实现
 */
@Service
public class LeaseAgreementServiceImpl implements LeaseAgreementService {

    private final LeaseAgreementRepository leaseAgreementRepository;
    private final LeaseAgreementMapper leaseAgreementMapper;
    private final GraphInfoRepository graphInfoRepository;

    @Autowired
    public LeaseAgreementServiceImpl(LeaseAgreementRepository leaseAgreementRepository,
                                     LeaseAgreementMapper leaseAgreementMapper, GraphInfoRepository graphInfoRepository) {
        this.leaseAgreementRepository = leaseAgreementRepository;
        this.leaseAgreementMapper = leaseAgreementMapper;
        this.graphInfoRepository = graphInfoRepository;
    }

    @Override
    @Transactional
    public void saveOrUpdate(LeaseAgreement leaseAgreement) {
        leaseAgreementRepository.save(leaseAgreement);
    }

    @Override
    @Transactional
    public void updateStatusByValue(Long id, LeaseStatus leaseStatus) {
        leaseAgreementRepository.updateStatusById(leaseStatus, id);
    }

    @Override
    public AgreementDetailVo getDetailById(Long id) {
        LeaseAgreement leaseAgreement = leaseAgreementRepository.findWithAllValuesById(id);
        AgreementDetailVo vo = leaseAgreementMapper.toDetailVo(leaseAgreement);
        vo.setApartmentGraphVoList(graphInfoRepository.findApartmentGraphVoByApartmentId(vo.getApartmentId()));
        vo.setRoomGraphVoList(graphInfoRepository.findRoomGraphVoByRoomId(vo.getRoomId()));
        return vo;
    }

    @Override
    public List<AgreementItemVo> listItem() {
        List<LeaseAgreement> list = leaseAgreementRepository.findList();
        List<AgreementItemVo> voList = leaseAgreementMapper.toItemVoList(list);
        List<Long> roomIds = voList.stream().map(AgreementItemVo::getRoomId).toList();
        Map<Long, List<GraphVo>> graphMap = graphInfoRepository.findRoomGraphVoByRoomIds(roomIds)
                .stream()
                .collect(Collectors.groupingBy(
                        RoomGraphVo::getRoomId,
                        Collectors.mapping(vo -> new GraphVo(vo.getName(), vo.getUrl()), Collectors.toList())
                ));
        voList.forEach(vo -> vo.setRoomGraphVoList(graphMap.getOrDefault(vo.getRoomId(), List.of())));
        return voList;
    }
}




