package ru.ershov.backend.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.backend.dto.ShoppingCartDto;
import ru.ershov.backend.entity.ShoppingCart;
import ru.ershov.backend.mapper.AbstractMapper;

@Component
public class ShoppingCartMapper extends AbstractMapper<ShoppingCart, ShoppingCartDto> {
    protected ShoppingCartMapper() {
        super(ShoppingCart.class, ShoppingCartDto.class);
        getMapper().createTypeMap(ShoppingCart.class, ShoppingCartDto.class)
                .addMappings(m -> m.skip(ShoppingCartDto::setItemId))
                .setPostConverter(context -> {
                    ShoppingCart source = context.getSource();
                    ShoppingCartDto destination = context.getDestination();
                    destination.setItemId(source.getItemId());
                    return context.getDestination();
                });
    }
}
