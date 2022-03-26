package ru.ershov.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ershov.backend.dto.ItemDto;
import ru.ershov.backend.dto.OrderDetailDto;
import ru.ershov.backend.entity.OrderDetail;
import ru.ershov.backend.mapper.impl.OrderDetailMapper;
import ru.ershov.backend.repository.OrderDetailRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailMapper orderDetailMapper;
    private final OrderDetailRepository orderDetailRepository;

    protected List<OrderDetailDto> getAllByOrderId(Long orderId) {
        return orderDetailMapper.toDtos(orderDetailRepository.getAllByOrder(orderId));
    }

    public List<OrderDetailDto> getAllByOrderIdAndPersonId(Long orderId, Long personId) {
        return orderDetailMapper.toDtos(orderDetailRepository.getAllByOrderAndPersonId(orderId, personId));
    }

    public List<OrderDetailDto> getAllByOrderIdAndCompanyId(Long orderId, Long companyId) {
        return orderDetailMapper.toDtos(orderDetailRepository.getAllByOrderIdAndCompanyId(orderId, companyId));
    }

    protected List<OrderDetailDto> createAllFromCartItem(Long orderId, List<ItemDto> itemDto) {
        List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
        for (ItemDto dto : itemDto) {
            var orderDetail = new OrderDetail(dto.getAmount(), dto.getId(), orderId);
            orderDetailDtos.add(orderDetailMapper.toDto(orderDetailRepository.save(orderDetail)));
        }
        return orderDetailDtos;
    }
}
