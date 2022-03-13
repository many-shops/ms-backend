package ru.ershov.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ershov.backend.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
