package ru.ershov.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ershov.backend.dto.CreateOrderDto;
import ru.ershov.backend.dto.ItemDto;
import ru.ershov.backend.dto.OrderDetailDto;
import ru.ershov.backend.dto.OrderDto;
import ru.ershov.backend.entity.Order;
import ru.ershov.backend.entity.Person;
import ru.ershov.backend.mapper.impl.OrderMapper;
import ru.ershov.backend.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;

    private final ItemService itemService;

    public List<OrderDto> getAllByCompanyId(Long companyId) {
        List<OrderDto> orderDtos = orderMapper.toDtos(orderRepository.getAllOrderByCompanyId(companyId));
        orderDtos.forEach(orderDto ->
                orderDto.setOrderDetails(orderDetailService.getAllByOrderId(orderDto.getId())));
        return orderDtos;
    }

    public OrderDto getByIdAndCompanyId(Long orderId, Long companyId) {
        OrderDto orderDto = orderMapper.toDto(orderRepository.findByIdAndCompanyId(orderId, companyId).orElseThrow());
        orderDto.setOrderDetails(orderDetailService.getAllByOrderId(orderDto.getId()));
        return orderDto;
    }

    public List<OrderDto> getAllByPersonId(Long personId) {
        List<OrderDto> orderDtos = orderMapper.toDtos(orderRepository.getAllByPersonId(personId));
        orderDtos.forEach(orderDto ->
                orderDto.setOrderDetails(orderDetailService.getAllByOrderId(orderDto.getId())));
        return orderDtos;
    }

    public OrderDto updateStatus(Long id, Long companyId, OrderDto orderDto) {
        var byId = orderRepository.findByIdAndCompanyId(id, companyId).orElseThrow();
        orderMapper.updateEntity(byId, orderDto);
        orderRepository.save(byId);
        return orderMapper.toDto(byId);
    }

    public List<OrderDto> createOrder(Person person, CreateOrderDto createOrderDto) {
        List<OrderDto> orderDtos = new ArrayList<>();
        createOrderDto.getShoppingCarts().stream()
                .map(shoppingCartDto -> {
                    ItemDto byIdInternal = itemService.getByIdInternal(shoppingCartDto.getItemId());
                    byIdInternal.setAmount(shoppingCartDto.getAmount());
                    return byIdInternal;
                })
                .collect(Collectors.groupingBy(ItemDto::getCompanyId))
                .forEach((companyId, itemDtos) -> {
                    var order = new Order(createOrderDto.getAddress(), "OPEN", person);
                    var saveOrder = orderRepository.save(order);
                    List<OrderDetailDto> orderDetailDtos =
                            orderDetailService.createAllFromCartItem(saveOrder.getId(), itemDtos);
                    var orderDto = orderMapper.toDto(order);
                    orderDto.setOrderDetails(orderDetailDtos);
                    orderDtos.add(orderDto);
                });
        return orderDtos;
    }
}
