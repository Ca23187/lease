package com.lease.model.entity;

import com.lease.model.enums.ItemType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Schema(description = "图片信息表")
@Setter
@Getter
@Entity
@Table(name = "graph_info")
public class GraphInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "图片名称")
    @Column(name = "name")
    private String name;

    @Schema(description = "图片所属对象类型")
    @Column(name = "item_type")
    @Convert(converter = ItemType.ItemTypeToIntegerConverter.class)
    private ItemType itemType;

    @Schema(description = "图片所有对象id")
    @Column(name = "item_id")
    private Long itemId;

    @Schema(description = "图片地址")
    @Column(name = "url")
    private String url;

}