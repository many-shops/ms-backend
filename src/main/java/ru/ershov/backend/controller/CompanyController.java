package ru.ershov.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ershov.backend.dto.CompanyDto;
import ru.ershov.backend.service.CompanyService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/companies")
@PreAuthorize("isAuthenticated()")
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
