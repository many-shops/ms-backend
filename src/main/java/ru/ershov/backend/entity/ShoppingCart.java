package ru.ershov.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "shopping_cart")
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart  extends AbstractEntity {
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;

    @Column(name = "person_id")
    private Long personId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private Item item;

    @Column(name = "item_id")
    private Long itemId;

    public ShoppingCart(Long amount,  Long personId, Long itemId) {
        this.amount = amount;
        this.personId = personId;
        this.itemId = itemId;
    }
}
