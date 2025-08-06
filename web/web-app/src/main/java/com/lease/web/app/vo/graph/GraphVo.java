package com.lease.web.app.vo.graph;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@Getter
@Setter
@Schema(description = "图片信息")
public class GraphVo implements Serializable {


    @Schema(description = "图片名称")
    private String name;

    @Schema(description = "图片地址")
    private String url;

}
