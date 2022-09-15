package ru.ershov.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ershov.backend.dto.CreateOrderDto;
import ru.ershov.backend.dto.OrderDto;
import ru.ershov.backend.entity.Person;
import ru.ershov.backend.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/order")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getCurrentSessionOrders(@AuthenticationPrincipal Person person) {
        return orderService.getAllByPersonId(person.getId());
    }

    @PostMapping
    public List<OrderDto> createOrder(
            @Valid @RequestBody CreateOrderDto createOrderDto,
            @AuthenticationPrincipal Person person
    ) {
        return orderService.createOrder(person, createOrderDto);
    }
}
