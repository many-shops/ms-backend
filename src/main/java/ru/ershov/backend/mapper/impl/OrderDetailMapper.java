package ru.ershov.backend.mapper.impl;

import org.springframework.context.annotation.Configuration;
import ru.ershov.backend.dto.OrderDetailDto;
import ru.ershov.backend.entity.OrderDetail;
import ru.ershov.backend.mapper.AbstractMapper;

@Configuration
public class OrderDetailMapper extends AbstractMapper<OrderDetail, OrderDetailDto> {

    protected OrderDetailMapper() {
        super(OrderDetail.class, OrderDetailDto.class);
        getMapper().createTypeMap(OrderDetail.class, OrderDetailDto.class)
                .addMappings(m -> m.skip(OrderDetailDto::setOrderId))
                .setPostConverter(context -> {
                    OrderDetail source = context.getSource();
                    OrderDetailDto destination = context.getDestination();
                    destination.setOrderId(source.getOrderId());
                    return context.getDestination();
                })
                .addMappings(m -> m.skip(OrderDetailDto::setItemId))
                .setPostConverter(context -> {
                    OrderDetail source = context.getSource();
                    OrderDetailDto destination = context.getDestination();
                    destination.setItemId(source.getItemId());
                    return context.getDestination();
                });
    }
}
