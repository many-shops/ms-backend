package ru.ershov.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ershov.backend.entity.ShoppingCart;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    List<ShoppingCart> findAllByPersonId(Long personId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "DELETE FROM shopping_cart " +
                    "WHERE person_id = :person_id AND item_id = :item_id")
    void deleteByPersonIdAndId(@Param("person_id") Long personId, @Param("item_id") Long itemId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "DELETE FROM shopping_cart " +
                    "WHERE person_id = :person_id")
    void deleteByPersonId(@Param("person_id") Long personId);

    Optional<ShoppingCart> getByPersonIdAndItemId(Long personId, Long itemId);
}
