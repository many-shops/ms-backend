package ru.ershov.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ershov.backend.dto.CreateOrderDto;
import ru.ershov.backend.dto.ItemDto;
import ru.ershov.backend.dto.ShoppingCartDto;
import ru.ershov.backend.entity.Person;
import ru.ershov.backend.service.ShoppingCartService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/shopping-cart")
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    public List<ItemDto> getCartByCurrentSession(
            @AuthenticationPrincipal Person person
    ) {
        return shoppingCartService.getByPersonId(person.getId());
    }

    @PostMapping
    public ShoppingCartDto addItemInCartByCurrentSession(
            @Valid @RequestBody ShoppingCartDto shoppingCartDto,
            @AuthenticationPrincipal Person person
    ) {
        return shoppingCartService.create(person.getId(), shoppingCartDto);
    }

    @PutMapping
    public ShoppingCartDto updateItemInCartByCurrentSession(
            @Valid @RequestBody ShoppingCartDto shoppingCartDto,
            @AuthenticationPrincipal Person person
    ) {
        return shoppingCartService.update(person.getId(), shoppingCartDto);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCartByCurrentSession(
            @AuthenticationPrincipal Person person
    ) {
        shoppingCartService.deleteByPersonId(person.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/item/{item_id}")
    public ResponseEntity<Object> deleteItemInCartByCurrentSession(
            @PathVariable("item_id") Long itemId,
            @AuthenticationPrincipal Person person
    ) {
        shoppingCartService.deleteByPersonIdAndId(person.getId(), itemId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
