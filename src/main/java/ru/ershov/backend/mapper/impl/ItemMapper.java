package ru.ershov.backend.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.backend.dto.ItemDto;
import ru.ershov.backend.entity.Item;
import ru.ershov.backend.mapper.AbstractMapper;

@Component
public class ItemMapper extends AbstractMapper<Item, ItemDto> {
    protected ItemMapper() {
        super(Item.class, ItemDto.class);
        getMapper().createTypeMap(Item.class, ItemDto.class)
                .addMappings(m -> m.skip(ItemDto::setCompanyId))
                .setPostConverter(context -> {
                    Item source = context.getSource();
                    ItemDto destination = context.getDestination();
                    destination.setCompanyId(source.getCompany().getId());
                    return context.getDestination();
                });
    }

}
