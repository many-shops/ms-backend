package ru.ershov.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "updated_time", insertable = false, updatable = false)
    private Timestamp updatedTime;

    @Column(name = "created_time", insertable = false, updatable = false)
    private Timestamp createTime;

}
