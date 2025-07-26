package com.lease.web.admin.service;

import com.lease.model.entity.PaymentType;

import java.util.List;

/**
* @description 针对表【payment_type(支付方式表)】的数据库操作Service
*/
public interface PaymentTypeService {
    public List<PaymentType> list();

    public void saveOrUpdate(PaymentType paymentType);

    void removeById(Long id);

}
