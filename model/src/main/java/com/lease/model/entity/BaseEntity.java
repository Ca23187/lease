package com.lease.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@SuperBuilder  // 父子类都需要使用这个注解来取代Builder
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass  // 父类需要定义该注解，这样子类就不用标记ID了
@EntityListeners(AuditingEntityListener.class)  // 支持自动创建日期和更新日期
public abstract class BaseEntity implements Serializable {

    @Schema(description = "主键")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "创建时间")
    @Column(name = "create_time", updatable = false)
    @JsonIgnore
    @CreatedDate
    private Date createTime;

    @Schema(description = "更新时间")
    @Column(name = "update_time")
    @JsonIgnore
    @LastModifiedDate
    private Date updateTime;

    @Schema(description = "逻辑删除")
    @Column(name = "is_deleted")
    @JsonIgnore
    private Byte isDeleted = 0;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        BaseEntity that = (BaseEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}