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

@Schema(description = "杂项费用名称表")
@Setter
@Getter
@Entity
@SQLDelete(sql = "UPDATE fee_key SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted = 0")
@Table(name = "fee_key")
public class FeeKey extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "付款项key")
    @Column(name = "name")
    private String name;

    @Schema(hidden = true)
    @JsonManagedReference  // 阻止OneToMany和ManyToOne同时使用导致的递归序列化
    @OneToMany(mappedBy = "feeKey", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FeeValue> feeValueList;

}