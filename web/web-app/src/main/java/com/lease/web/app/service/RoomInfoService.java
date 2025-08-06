package com.lease.web.app.service;

import com.lease.web.app.dto.room.RoomQueryDto;
import com.lease.web.app.vo.room.RoomDetailVo;
import com.lease.web.app.vo.room.RoomItemVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
* @description 针对表【room_info(房间信息表)】的数据库操作Service
*/
public interface RoomInfoService {
    Page<RoomItemVo> pageItem(RoomQueryDto queryDto, Pageable pageable);

    Page<RoomItemVo> pageItemByApartmentId(Long id, Pageable pageable);

    RoomDetailVo getDetailById(Long id);
}
