package ru.ershov.backend.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class Person extends AbstractEntity implements UserDetails {
    private String username;
    private String name;
    private String surname;
    @Column(name = "vk_link")
    private String vkLink;
    private String email;
    private String password;
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "person__role",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "seller",fetch = FetchType.LAZY)
    private transient List<Company> companies = new ArrayList<>();

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<ShoppingCart> shoppingCarts;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
