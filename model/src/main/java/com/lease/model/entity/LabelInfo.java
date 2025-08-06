package com.lease.model.entity;

import com.lease.model.enums.ItemType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;

@Schema(description = "标签信息表")
@Setter
@Getter
@Entity
@NoArgsConstructor
@SQLDelete(sql = "UPDATE label_info SET is_deleted = 1, update_time = now() WHERE id = ?")
@Where(clause = "is_deleted = 0")
@Table(name = "label_info")
public class LabelInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "类型")
    @Column(name = "type")
    @Convert(converter = ItemType.ItemTypeToIntegerConverter.class)
    private ItemType type;

    @Schema(description = "标签名称")
    @Column(name = "name")
    private String name;

    public LabelInfo(Long id, ItemType type, String name) {
        super.setId(id);
        this.type = type;
        this.name = name;
    }

}