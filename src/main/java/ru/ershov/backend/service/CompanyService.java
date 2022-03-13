package ru.ershov.backend.service;

import org.springframework.stereotype.Service;
import ru.ershov.backend.dto.CompanyDto;
import ru.ershov.backend.entity.Company;
import ru.ershov.backend.entity.Person;
import ru.ershov.backend.mapper.impl.CompanyMapper;
import ru.ershov.backend.repository.CompanyRepository;

@Service
public class CompanyService extends CrudService<Company, CompanyDto> {
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyMapper mapper, CompanyRepository repository) {
        super(mapper, repository);
        this.companyMapper = mapper;
    }

    public CompanyDto insert(CompanyDto dto, Person person) {
        var company = companyMapper.toEntity(dto);
        company.setPerson(person);
        return insert(company);
    }
}
