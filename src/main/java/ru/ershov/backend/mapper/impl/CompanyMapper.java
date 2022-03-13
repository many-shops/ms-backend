package ru.ershov.backend.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.backend.dto.CompanyDto;
import ru.ershov.backend.entity.Company;
import ru.ershov.backend.mapper.AbstractMapper;

@Component
public class CompanyMapper extends AbstractMapper<Company, CompanyDto> {
    protected CompanyMapper() {
        super(Company.class, CompanyDto.class);
        getMapper().createTypeMap(Company.class, CompanyDto.class)
                .addMappings(m -> m.skip(CompanyDto::setAdminId))
                .setPostConverter(context -> {
                    Company source = context.getSource();
                    CompanyDto destination = context.getDestination();
                    destination.setAdminId(source.getPerson().getId());
                    return context.getDestination();
                });
    }
}
