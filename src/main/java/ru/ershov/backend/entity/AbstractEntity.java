package ru.ershov.backend.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "updated_time", insertable = false, updatable = false)
    private Timestamp updatedTime;

    @Column(name = "created_time", insertable = false, updatable = false)
    private Timestamp createTime;

}
