package com.lease.web.admin.service.impl;

import com.lease.model.entity.LabelInfo;
import com.lease.model.enums.ItemType;
import com.lease.web.admin.repository.LabelInfoRepository;
import com.lease.web.admin.service.LabelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description 针对表【label_info(标签信息表)】的数据库操作Service实现
*/
@Service
public class LabelInfoServiceImpl implements LabelInfoService {

    private final LabelInfoRepository repository;

    @Autowired
    public LabelInfoServiceImpl(LabelInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LabelInfo> list(ItemType type) {
        if (type != null) {
            return repository.findByType(type);
        }
        return repository.findAll();
    }

    @Override
    public void saveOrUpdate(LabelInfo labelInfo) {
        repository.save(labelInfo);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}




