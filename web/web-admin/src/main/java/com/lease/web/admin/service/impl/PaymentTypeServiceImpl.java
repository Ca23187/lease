package com.lease.web.admin.service.impl;

import com.lease.model.entity.PaymentType;
import com.lease.web.admin.repository.PaymentTypeRepository;
import com.lease.web.admin.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description 针对表【payment_type(支付方式表)】的数据库操作Service实现
*/
@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {

    private final PaymentTypeRepository repository;

    @Autowired
    public PaymentTypeServiceImpl(PaymentTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PaymentType> list() {
        return repository.findAll();
    }

    @Override
    public void saveOrUpdate(PaymentType paymentType) {
        repository.save(paymentType);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}




