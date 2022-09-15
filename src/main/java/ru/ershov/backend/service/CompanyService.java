package ru.ershov.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ershov.backend.dto.CompanyDto;
import ru.ershov.backend.entity.Company;
import ru.ershov.backend.entity.Person;
import ru.ershov.backend.mapper.impl.CompanyMapper;
import ru.ershov.backend.repository.CompanyRepository;

import java.util.List;

@Slf4j
@Service
public class CompanyService extends CrudService<Company, CompanyDto> {
    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyMapper mapper, CompanyRepository repository) {
        super(mapper, repository);
        this.companyMapper = mapper;
        this.companyRepository = repository;
    }

    public List<CompanyDto> getAllBySellerId(Long sellerId) {
        return companyMapper.toDtos(companyRepository.getAllBySellerId(sellerId));
    }

    public CompanyDto insert(CompanyDto dto, Person person) {
        var company = companyMapper.toEntity(dto);
        company.setSeller(person);
        return insert(company);
    }

    public boolean isSellerIsOwner(Long companyId, Long sellerId) {
        log.info("isSellerOwner({}, {})", companyId, sellerId);
        return companyRepository.isSellerOwner(companyId, sellerId);
    }
}
