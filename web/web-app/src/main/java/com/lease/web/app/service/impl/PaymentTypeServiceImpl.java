package com.lease.web.app.service.impl;

import com.lease.model.entity.PaymentType;
import com.lease.web.app.repository.PaymentTypeRepository;
import com.lease.web.app.repository.RoomInfoRepository;
import com.lease.web.app.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description 针对表【payment_type(支付方式表)】的数据库操作Service实现
*/
@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepository;
    private final RoomInfoRepository roomInfoRepository;

    @Autowired
    public PaymentTypeServiceImpl(PaymentTypeRepository paymentTypeRepository, RoomInfoRepository roomInfoRepository) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.roomInfoRepository = roomInfoRepository;
    }

    @Override
    public List<PaymentType> list() {
        return paymentTypeRepository.findAll();
    }

    @Override
    public List<PaymentType> findPaymentTypeListByRoomId(Long id) {
        return roomInfoRepository.findPaymentTypeListById(id);
    }
}




