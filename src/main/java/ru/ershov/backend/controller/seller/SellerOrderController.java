package ru.ershov.backend.controller.seller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ershov.backend.dto.OrderDto;
import ru.ershov.backend.service.OrderService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/seller/companies/{company_id}/orders")
@RequiredArgsConstructor
public class SellerOrderController {

    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or (hasRole('SELLER') and @sellerCompanyController.isSellerOwner(#companyId))")
    public List<OrderDto> getAllOrder(
            @PathVariable("company_id") Long companyId
    ) {
        return orderService.getAllByCompanyId(companyId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('SELLER') and @sellerCompanyController.isSellerOwner(#companyId))")
    public OrderDto getAllOrder(
            @PathVariable("company_id") Long companyId,
            @PathVariable("id") Long orderId
    ) {
        return orderService.getByIdAndCompanyId(companyId, orderId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('SELLER') and @sellerCompanyController.isSellerOwner(#companyId))")
    public OrderDto updateStatus(
            @PathVariable("company_id") Long companyId,
            @PathVariable("id") Long orderId,
            @RequestBody OrderDto orderDto
    ) {
        return orderService.updateStatus(companyId, orderId, orderDto);
    }
}
