package ru.ershov.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.ershov.backend.dto.CompanyDto;
import ru.ershov.backend.entity.Company;
import ru.ershov.backend.entity.Person;
import ru.ershov.backend.service.CompanyService;

import javax.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController extends CrudController<Company, CompanyDto>{

    private final CompanyService companyService;

    public CompanyController(CompanyService service) {
        super(service);
        this.companyService = service;
    }

    @PostMapping
    @Operation(description = "Добавляет объект")
    public CompanyDto insert(
            @Valid @RequestBody CompanyDto dto,
            @AuthenticationPrincipal Person person
    ) {
        return companyService.insert(dto, person);
    }
}
