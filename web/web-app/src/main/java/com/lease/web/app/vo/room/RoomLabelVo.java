package com.lease.web.app.vo.room;

import com.lease.model.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RoomLabelVo {
    private Long roomId;
    private Long labelId;
    private ItemType type;
    private String labelName;
}
