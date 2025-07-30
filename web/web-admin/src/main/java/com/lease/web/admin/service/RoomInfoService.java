package com.lease.web.admin.service;

import com.lease.web.admin.dto.room.RoomSubmitDto;
import com.lease.web.admin.vo.room.RoomDetailVo;
import com.lease.web.admin.vo.room.RoomInfoVo;
import com.lease.web.admin.vo.room.RoomItemVo;
import com.lease.web.admin.vo.room.RoomQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
* @description 针对表【room_info(房间信息表)】的数据库操作Service
*/
public interface RoomInfoService {

    void saveOrUpdate(RoomSubmitDto dto);

    Page<RoomItemVo> pageRooms(RoomQueryVo queryVo, Pageable pageable);

    RoomDetailVo getDetailById(Long id);

    void removeById(Long id);

    List<RoomInfoVo> listBasicByApartmentId(Long id);
}
