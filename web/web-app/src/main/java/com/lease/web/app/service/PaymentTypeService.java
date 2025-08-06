package com.lease.web.app.service;

import com.lease.model.entity.PaymentType;

import java.util.List;

/**
* @description 针对表【payment_type(支付方式表)】的数据库操作Service
*/
public interface PaymentTypeService {
    List<PaymentType> list();

    List<PaymentType> findPaymentTypeListByRoomId(Long id);
}
