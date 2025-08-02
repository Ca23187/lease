package com.lease.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.util.List;

@Schema(description = "房间基本属性表")
@Setter
@Getter
@Entity
@SQLDelete(sql = "UPDATE attr_key SET is_deleted = 1, update_time = now() WHERE id = ?")
@Where(clause = "is_deleted = 0")
@Table(name = "attr_key")
public class AttrKey extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "属性key")
    @Column(name = "name")
    private String name;

    @Schema(hidden = true)
    @JsonManagedReference
    @OneToMany(mappedBy = "attrKey", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AttrValue> attrValueList;

}