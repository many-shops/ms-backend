package ru.ershov.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item extends AbstractEntity {
    private String name;
    private String description;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<ShoppingCart> shoppingCarts;
}
