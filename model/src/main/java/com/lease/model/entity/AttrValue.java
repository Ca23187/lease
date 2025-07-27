package com.lease.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;

@Schema(description = "房间基本属性值表")
@Setter
@Getter
@Entity
@SQLDelete(sql = "UPDATE attr_value SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted = 0")
@Table(name = "attr_value")
public class AttrValue extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "属性value")
    @Column(name = "name")
    private String name;

    @JsonBackReference  // 阻止OneToMany和ManyToOne同时使用导致的递归序列化
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attr_key_id")
    private AttrKey attrKey;
}