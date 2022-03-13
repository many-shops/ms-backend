package ru.ershov.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company extends AbstractEntity {
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Person person;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();
}
