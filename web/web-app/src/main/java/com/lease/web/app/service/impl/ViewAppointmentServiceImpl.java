package com.lease.web.app.service.impl;

import com.lease.model.entity.ViewAppointment;
import com.lease.web.app.mapper.AppointmentMapper;
import com.lease.web.app.repository.GraphInfoRepository;
import com.lease.web.app.repository.RoomInfoRepository;
import com.lease.web.app.repository.ViewAppointmentRepository;
import com.lease.web.app.service.ViewAppointmentService;
import com.lease.web.app.vo.appointment.AppointmentDetailVo;
import com.lease.web.app.vo.appointment.AppointmentItemVo;
import com.lease.web.app.vo.graph.ApartmentGraphVo;
import com.lease.web.app.vo.graph.GraphVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service实现
 */
@Service
public class ViewAppointmentServiceImpl implements ViewAppointmentService {

    private final ViewAppointmentRepository viewAppointmentRepository;
    private final GraphInfoRepository graphInfoRepository;
    private final AppointmentMapper appointmentMapper;
    private final RoomInfoRepository roomInfoRepository;

    @Autowired
    public ViewAppointmentServiceImpl(ViewAppointmentRepository viewAppointmentRepository, GraphInfoRepository graphInfoRepository,
                                      AppointmentMapper appointmentMapper, RoomInfoRepository roomInfoRepository) {
        this.viewAppointmentRepository = viewAppointmentRepository;
        this.graphInfoRepository = graphInfoRepository;
        this.appointmentMapper = appointmentMapper;
        this.roomInfoRepository = roomInfoRepository;
    }

    @Override
    public void saveOrUpdate(ViewAppointment viewAppointment) {
        viewAppointmentRepository.save(viewAppointment);
    }

    @Override
    public List<AppointmentItemVo> listItem(Long userId) {
        List<AppointmentItemVo> voList = viewAppointmentRepository.findItemsByUserId(userId);
        List<Long> apartmentIds = voList.stream().map(AppointmentItemVo::getApartmentId).toList();
        Map<Long, List<GraphVo>> graphMap = graphInfoRepository.findApartmentGraphVoByApartmentIds(apartmentIds)
                .stream()
                .collect(Collectors.groupingBy(
                        ApartmentGraphVo::getApartmentId,
                        Collectors.mapping(vo -> new GraphVo(vo.getName(), vo.getUrl()), Collectors.toList())
                ));
        for (AppointmentItemVo vo : voList) {
            vo.setGraphVoList(graphMap.getOrDefault(vo.getApartmentId(), List.of()));
        }
        return voList;
    }

    @Override
    public AppointmentDetailVo getDetailById(Long id) {
        ViewAppointment viewAppointment = viewAppointmentRepository.findWithApartmentInfoById(id);
        AppointmentDetailVo vo = appointmentMapper.toItemVo(viewAppointment);
        vo.getApartmentItemVo().setMinRent(roomInfoRepository.findMinRentByApartmentInfo_Id(vo.getApartmentId()));
        vo.getApartmentItemVo().setGraphVoList(graphInfoRepository.findApartmentGraphVoByApartmentId(vo.getApartmentId()));
        return vo;
    }
}




