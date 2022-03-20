package ru.ershov.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ershov.backend.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(nativeQuery = true,
            value = "select case when count(id)> 0 then true else false end " +
                    "from company " +
                    "where id = :companyId and admin_id = :sellerId")
    boolean isSellerOwner(@Param("companyId") Long companyId, @Param("sellerId") Long sellerId);
}
