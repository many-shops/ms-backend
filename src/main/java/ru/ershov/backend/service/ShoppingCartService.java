package ru.ershov.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ershov.backend.dto.ItemDto;
import ru.ershov.backend.dto.ShoppingCartDto;
import ru.ershov.backend.entity.Person;
import ru.ershov.backend.entity.ShoppingCart;
import ru.ershov.backend.mapper.impl.ItemMapper;
import ru.ershov.backend.mapper.impl.ShoppingCartMapper;
import ru.ershov.backend.repository.ShoppingCartRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ItemService itemService;
    private final PersonService personService;
    private final ItemMapper itemMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    public List<ItemDto> getByPersonId(Long personId) {
        return shoppingCartRepository.findAllByPersonId(personId).stream()
                .map(shoppingCart -> {
                    var itemDto = itemMapper.toDto(shoppingCart.getItem());
                    itemDto.setAmount(shoppingCart.getAmount());
                    return itemDto;
                })
                .collect(Collectors.toList());
    }

    public ShoppingCartDto createAdmin(Long personId, Long itemId, Long amount) {
        if (!personService.existById(personId)) {
            throw new IllegalArgumentException("no person by id");
        }
        if (!itemService.existById(itemId)) {
            throw new IllegalArgumentException("no item by id");
        }
        var shoppingCart = new ShoppingCart(amount, personId, itemId);
        return shoppingCartMapper.toDto(
                shoppingCartRepository.save(shoppingCart)
        );
    }

    public ShoppingCartDto create(Long personId, Long itemId, Long amount) {
        return createAdmin(personId, itemId, amount);
    }

    public ShoppingCartDto create(Long personId, ShoppingCartDto shoppingCartDto) {
        return createAdmin(personId, shoppingCartDto.getItemId(), shoppingCartDto.getAmount());
    }

    public ShoppingCartDto update(Long personId, ShoppingCartDto shoppingCartDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.getByPersonIdAndItemId(personId, shoppingCartDto.getItemId()).orElseThrow();
        shoppingCart.setAmount(shoppingCartDto.getAmount());
        shoppingCartRepository.save(shoppingCart);
        return shoppingCartDto;
    }

    public void deleteByPersonIdAndId(Long personId, Long itemId) {
        shoppingCartRepository.deleteByPersonIdAndId(personId, itemId);
    }

    public void deleteByPersonId(Long personId) {
        shoppingCartRepository.deleteByPersonId(personId);
    }
}
