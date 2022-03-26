package ru.ershov.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.backend.dto.ItemDto;
import ru.ershov.backend.service.ItemService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/companies/{company_id}/items")
@PreAuthorize("hasRole('USER')")
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
