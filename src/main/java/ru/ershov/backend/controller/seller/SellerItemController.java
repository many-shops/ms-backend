package ru.ershov.backend.controller.seller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ershov.backend.dto.ItemDto;
import ru.ershov.backend.service.ItemService;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@Validated
@RestController
@RequestMapping("/api/seller/companies/{company_id}/items")
@RequiredArgsConstructor
public class SellerItemController {

    private final ItemService itemService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or (hasRole('SELLER') and @sellerCompanyController.isSellerOwner(#companyId))")
    public ItemDto create(
            @PathVariable("company_id") Long companyId,
            @Valid @RequestBody ItemDto itemDto
    ) {
        return itemService.insert(companyId, itemDto);
    }

    @PutMapping("/{item_id}")
    @PreAuthorize("hasRole('ADMIN') || (hasRole('SELLER') && @sellerCompanyController.isSellerOwner(#companyId))")
    public ItemDto update(
            @PathVariable("company_id") Long companyId,
            @PathVariable("item_id") Long itemId,
            @Valid @RequestBody ItemDto itemDto
    ) {
        return itemService.update(companyId, itemId,  itemDto);
    }

    @DeleteMapping("/{item_id}")
    @PreAuthorize("hasRole('ADMIN') || (hasRole('SELLER') && @sellerCompanyController.isSellerOwner(#companyId))")
    public ResponseEntity<Object> delete(
            @PathVariable("company_id") Long companyId,
            @PathVariable("item_id") Long itemId
    ) {
        itemService.delete(companyId, itemId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
