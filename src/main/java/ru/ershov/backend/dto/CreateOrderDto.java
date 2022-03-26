package ru.ershov.backend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto  {
    private String address;
    private List<ShoppingCartDto> shoppingCarts;
}
