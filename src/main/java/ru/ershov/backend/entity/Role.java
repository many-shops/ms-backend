package ru.ershov.backend.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractEntity implements GrantedAuthority {
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Person> persons;

    @Override
    public String getAuthority() {
        return name;
    }
}
