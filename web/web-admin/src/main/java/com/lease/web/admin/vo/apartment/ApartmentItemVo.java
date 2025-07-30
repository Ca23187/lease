package com.lease.web.admin.vo.apartment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Schema(description = "后台管理系统公寓列表实体")
public class ApartmentItemVo extends ApartmentInfoVo{
    private Integer totalRoomCount;
    private Integer freeRoomCount;
}

