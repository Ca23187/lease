package com.lease.web.admin.service.impl;

import com.lease.model.entity.AttrKey;
import com.lease.web.admin.repository.AttrKeyRepository;
import com.lease.web.admin.service.AttrKeyService;
import com.lease.web.admin.vo.attr.AttrKeyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Service实现
*/
@Service
public class AttrKeyServiceImpl implements AttrKeyService {

    private final AttrKeyRepository repository;

    @Autowired
    public AttrKeyServiceImpl(AttrKeyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveAndUpdate(AttrKey attrKey) {
        repository.save(attrKey);
    }

    @Override
    public List<AttrKeyVo> listAttrInfo() {
        return repository.findAllWithValues().stream()
                .map(k -> new AttrKeyVo(k.getId(), k.getName(), k.getAttrValueList()))
                .collect(Collectors.toList());
    }

    @Override
    public void removeById(Long id) {
        // 为了确保级联删除生效，删除attrKey前需要查一下与之对应的attrValue
        repository.deleteById(id);
    }
}




