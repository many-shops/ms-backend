package ru.ershov.backend.service;

import org.springframework.stereotype.Service;
import ru.ershov.backend.dto.ItemDto;
import ru.ershov.backend.entity.Item;
import ru.ershov.backend.mapper.impl.ItemMapper;
import ru.ershov.backend.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final CompanyService companyService;

    public ItemService(ItemMapper mapper, ItemRepository repository, CompanyService companyService) {
        this.itemRepository = repository;
        this.itemMapper = mapper;
        this.companyService = companyService;
    }

    public List<ItemDto> getByCompanyId(Long id) {
        return itemRepository.findAllByCompanyId(id).stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    public ItemDto get(Long companyId, Long itemId) {
        return itemRepository.findById(companyId, itemId)
                .map(itemMapper::toDto)
                .orElseThrow();
    }

    protected Item getInternal(Long companyId, Long itemId) {
        return itemRepository.findById(companyId, itemId).orElseThrow();
    }

    public ItemDto insert(Long companyId, ItemDto itemDto) {
        var item = itemMapper.toEntity(itemDto);
        item.setCompany(companyService.getByIdInternal(companyId));
        return itemMapper.toDto(itemRepository.save(item));
    }

    public ItemDto update(Long companyId, Long itemId, ItemDto itemDto) {
        var item = getInternal(companyId, itemId);
        itemMapper.updateEntity(item, itemDto);
        return itemMapper.toDto(itemRepository.save(item));
    }

    public void delete(Long companyId, Long itemId) {
        itemRepository.deleteById(companyId, itemId);
    }
}
