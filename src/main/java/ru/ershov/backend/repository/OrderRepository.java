package ru.ershov.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ershov.backend.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM \"order\" o " +
                    "WHERE o.id = (" +
                    "   select od.id from order_detail od " +
                    "       join item i on od.item_id = i.id" +
                    "   where i.company_id = :company_id" +
                    "   limit 1" +
                    ")")
    List<Order> getAllOrderByCompanyId(@Param("company_id") Long companyId);

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM \"order\" o " +
                    "WHERE o.person_id = :person_id")
    List<Order> getAllByPersonId(@Param("person_id") Long personId);

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM \"order\" o " +
                    "WHERE o.id = :id and o.id = (" +
                    "   select od.id from order_detail od " +
                    "       join item i on od.item_id = i.id" +
                    "   where i.company_id = :company_id" +
                    "   limit 1" +
                    ")")
    Optional<Order> findByIdAndCompanyId(@Param("id") Long id, @Param("company_id") Long companyId);
}
