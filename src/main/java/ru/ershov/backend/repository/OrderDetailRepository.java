package ru.ershov.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ershov.backend.entity.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM order_detail " +
                    "WHERE order_id = :order_id")
    List<OrderDetail> getAllByOrder(@Param("order_id") Long orderId);


    @Query(nativeQuery = true,
            value = "SELECT od.id, od.order_id, od.item_id, od.amount, od.created_time, od.updated_time " +
                    "FROM order_detail od " +
                    "   Join \"order\" o ON o.id = od.order_id " +
                    "WHERE order_id = :order_id and o.person_id = :person_id")
    List<OrderDetail> getAllByOrderAndPersonId(@Param("order_id") Long orderId, @Param("person_id") Long personId);

    @Query(nativeQuery = true,
            value = "SELECT od.id, od.order_id, od.item_id, od.amount, od.created_time, od.updated_time " +
                    "FROM order_detail od " +
                    "   Join item i ON i.id = od.item_id " +
                    "WHERE order_id = :order_id and i.company_id = :company_id")
    List<OrderDetail> getAllByOrderIdAndCompanyId(@Param("order_id") Long orderId, @Param("company_id") Long companyId);
}
