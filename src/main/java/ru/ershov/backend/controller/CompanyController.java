package ru.ershov.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ershov.backend.dto.CompanyDto;
import ru.ershov.backend.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    @Operation(description = "Отдает массив объектов")
    public List<CompanyDto> findAll() {
        return companyService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(description = "Отдает объект по ID")
    public CompanyDto findById(
            @PathVariable("id") Long id
    ) {
        return companyService.getById(id);
    }
}
