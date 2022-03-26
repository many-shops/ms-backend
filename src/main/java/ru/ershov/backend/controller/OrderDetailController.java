package ru.ershov.backend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.backend.dto.OrderDetailDto;
import ru.ershov.backend.entity.Person;
import ru.ershov.backend.service.OrderDetailService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/order/{order_id}/detail")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<OrderDetailDto> getAllOrderDetail(
            @PathVariable("order_id") Long orderId,
            @AuthenticationPrincipal Person person
    ) {
        return orderDetailService.getAllByOrderIdAndPersonId(orderId, person.getId());
    }
}
