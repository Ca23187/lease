package com.lease.web.app.vo.history;


import com.lease.model.entity.BrowsingHistory;
import com.lease.web.app.vo.graph.GraphVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Schema(description = "浏览历史基本信息")
public class HistoryItemVo extends BrowsingHistory {

    @Schema(description = "房间号")
    private String roomNumber;

    @Schema(description = "租金")
    private BigDecimal rent;

    @Schema(description = "房间图片列表")
    private List<GraphVo> roomGraphVoList;

    @Schema(description = "公寓名称")
    private String apartmentName;

    @Schema(description = "省份名称")
    private String provinceName;

    @Schema(description = "城市名称")
    private String cityName;

    @Schema(description = "区县名称")
    private String districtName;

    public HistoryItemVo(Long id, Long userId, Long roomId, Date browseTime, String roomNumber, BigDecimal rent, String apartmentName, String provinceName, String cityName, String districtName) {
        super.setId(id);
        super.setUserId(userId);
        super.setRoomId(roomId);
        super.setBrowseTime(browseTime);
        this.roomNumber = roomNumber;
        this.rent = rent;
        this.apartmentName = apartmentName;
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.districtName = districtName;
    }
}
