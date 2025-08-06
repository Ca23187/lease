package com.lease.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.util.Date;

/**
 * @TableName browsing_history
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE browsing_history SET is_deleted = 1, update_time = now() WHERE id = ?")
@Where(clause = "is_deleted = 0")
@Table(name = "browsing_history")
public class BrowsingHistory extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    @Column(name = "user_id")
    private Long userId;

    @Schema(description = "房间id")
    @Column(name = "room_id")
    private Long roomId;

    @Schema(description = "浏览时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "browse_time")
    private Date browseTime;

}