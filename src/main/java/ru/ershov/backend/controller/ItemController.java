package ru.ershov.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ershov.backend.dto.ItemDto;
import ru.ershov.backend.service.CompanyService;
import ru.ershov.backend.service.ItemService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/company/{company_id}")
public class ItemController {

    private final CompanyService companyService;
    private final ItemService itemService;

    public ItemController(ItemService service, CompanyService companyService) {
        this.companyService = companyService;
        this.itemService = service;
    }

    @GetMapping("/item/{item_id}")
    public ItemDto getById(
            @PathVariable("company_id") Long companyId,
            @PathVariable("item_id") Long itemId
    ) {
        existCompany(companyId);
        return itemService.get(companyId, itemId);
    }

    @GetMapping("/item")
    public List<ItemDto> getAll(
            @PathVariable("company_id") Long companyId
    ) {
        existCompany(companyId);
        return itemService.getByCompanyId(companyId);
    }

    @PostMapping("/item")
    public ItemDto create(
            @PathVariable("company_id") Long companyId,
            @Valid @RequestBody ItemDto itemDto
    ) {
        return itemService.insert(companyId, itemDto);
    }

    @PutMapping("/item/{item_id}")
    public ItemDto update(
            @PathVariable("company_id") Long companyId,
            @PathVariable("item_id") Long itemId,
            @Valid @RequestBody ItemDto itemDto
    ) {
        return itemService.update(companyId, itemId,  itemDto);
    }

    @DeleteMapping("/item/{item_id}")
    public ResponseEntity<Object> createItem(
            @PathVariable("company_id") Long companyId,
            @PathVariable("item_id") Long itemId
    ) {
        existCompany(companyId);
        itemService.delete(companyId, itemId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private void existCompany(Long companyId) {
        if (!companyService.existById(companyId)) {
            throw new IllegalArgumentException("no company by id");
        }
    }
}
