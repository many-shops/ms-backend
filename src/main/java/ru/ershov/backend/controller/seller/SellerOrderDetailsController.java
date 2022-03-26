package ru.ershov.backend.controller.seller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.backend.dto.OrderDetailDto;
import ru.ershov.backend.service.OrderDetailService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/seller/companies/{company_id}/orders/{order_id}/details")
@RequiredArgsConstructor
public class SellerOrderDetailsController {

    private final OrderDetailService orderDetailService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or (hasRole('SELLER') and @sellerCompanyController.isSellerOwner(#companyId))")
    public List<OrderDetailDto> getAllOrderDetail(
            @PathVariable("company_id") Long companyId,
            @PathVariable("order_id") Long orderId
    ) {
        return orderDetailService.getAllByOrderIdAndCompanyId(orderId, companyId);
    }

}
