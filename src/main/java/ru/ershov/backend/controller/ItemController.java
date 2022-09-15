package ru.ershov.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ershov.backend.dto.ItemDto;
import ru.ershov.backend.service.ItemService;

import java.util.List;

@CrossOrigin(origins = "*")
@Validated
@RestController
@RequestMapping("/api/companies/{company_id}/items")
@PreAuthorize("hasAnyRole('USER', 'SELLER', 'ADMIN')")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getAll(
            @PathVariable("company_id") Long companyId
    ) {
        return itemService.getByCompanyId(companyId);
    }

    @GetMapping("/{item_id}")
    public ItemDto getById(
            @PathVariable("company_id") Long companyId,
            @PathVariable("item_id") Long itemId
    ) {
        return itemService.getByIdAndCompanyId(companyId, itemId);
    }

}
