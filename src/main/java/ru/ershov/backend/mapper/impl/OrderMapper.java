package ru.ershov.backend.mapper.impl;

import org.springframework.context.annotation.Configuration;
import ru.ershov.backend.dto.OrderDto;
import ru.ershov.backend.entity.Order;
import ru.ershov.backend.mapper.AbstractMapper;

@Configuration
public class OrderMapper extends AbstractMapper<Order, OrderDto> {

    protected OrderMapper() {
        super(Order.class, OrderDto.class);
        getMapper().createTypeMap(Order.class, OrderDto.class)
                .addMappings(m -> m.skip(OrderDto::setPersonId))
                .setPostConverter(context -> {
                    Order source = context.getSource();
                    OrderDto destination = context.getDestination();
                    destination.setPersonId(source.getPerson().getId());
                    return context.getDestination();
                });
    }
}
