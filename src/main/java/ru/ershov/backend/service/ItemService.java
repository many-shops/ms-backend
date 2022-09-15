package ru.ershov.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ershov.backend.dto.ItemDto;
import ru.ershov.backend.entity.Item;
import ru.ershov.backend.mapper.impl.ItemMapper;
import ru.ershov.backend.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final CompanyService companyService;

    private void existCompany(Long companyId) {
        if (!companyService.existById(companyId)) {
            throw new IllegalArgumentException("no company by id ");
        }
    }

    public List<ItemDto> getByCompanyId(Long companyId) {
        existCompany(companyId);
        return itemMapper.toDtos(itemRepository.findAllByCompanyId(companyId));
    }

    protected ItemDto getByIdInternal(Long itemId) {
        return itemRepository.findById(itemId)
                .map(itemMapper::toDto)
                .orElseThrow();
    }

    public ItemDto getByIdAndCompanyId(Long companyId, Long itemId) {
        return itemRepository.findById(companyId, itemId)
                .map(itemMapper::toDto)
                .orElseThrow();
    }

    protected Item getInternal(Long companyId, Long itemId) {
        return itemRepository.findById(companyId, itemId).orElseThrow();
    }

    protected boolean existById(Long id) {
        return itemRepository.existsById(id);
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
        existCompany(companyId);
        itemRepository.deleteById(companyId, itemId);
    }
}
