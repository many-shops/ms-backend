package ru.ershov.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ershov.backend.entity.Item;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM item " +
                    "WHERE company_id = :companyId")
    List<Item> findAllByCompanyId(@Param("companyId") Long companyId);

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM item " +
                    "WHERE company_id = :companyId AND id = :itemId")
    Optional<Item> findById(@Param("companyId") Long companyId, @Param("itemId") Long itemId);

    @Query(nativeQuery = true,
            value = "DELETE FROM item " +
                    "WHERE company_id = :companyId and id = :itemId")
    void deleteById(@Param("companyId") Long companyId, @Param("itemId") Long itemId);
}
