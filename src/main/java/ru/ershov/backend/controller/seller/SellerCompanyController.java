package ru.ershov.backend.controller.seller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.ershov.backend.dto.CompanyDto;
import ru.ershov.backend.entity.Person;
import ru.ershov.backend.service.CompanyService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/seller/companies")
@RequiredArgsConstructor
public class SellerCompanyController {

    private final CompanyService companyService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
    @Operation(description = "Список компаний продавца")
    public List<CompanyDto> getAllBySellerId(@AuthenticationPrincipal Person person){
        return companyService.getAllBySellerId(person.getId());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || (hasRole('SELLER') && @sellerCompanyController.isSellerOwner(#id))")
    @Operation(description = "Обновляет объект")
    public CompanyDto insert(
            @PathVariable("id") Long id,
            @Valid @RequestBody CompanyDto entity
    ) {
        return companyService.update(id, entity);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || (hasRole('SELLER') && @sellerCompanyController.isSellerOwner(#id))")
    @Operation(description = "Добавляет объект")
    public ResponseEntity<Object> delete(
            @PathVariable("id") Long id
    ) {
        companyService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('SELLER')")
    @Operation(description = "Добавляет объект")
    public CompanyDto insert(
            @Valid @RequestBody CompanyDto dto,
            @AuthenticationPrincipal Person person
    ) {
        return companyService.insert(dto, person);
    }

    public boolean isSellerOwner(Long companyId) {
        var person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return companyService.isSellerIsOwner(companyId, person.getId());
    }
}
