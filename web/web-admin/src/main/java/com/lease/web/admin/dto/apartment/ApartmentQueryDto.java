package com.lease.web.admin.dto.apartment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "公寓查询实体")
public class ApartmentQueryDto {

    @Schema(description = "省份id")
    private Long provinceId;

    @Schema(description = "城市id")
    private Long cityId;

    @Schema(description = "区域id")
    private Long districtId;
}
