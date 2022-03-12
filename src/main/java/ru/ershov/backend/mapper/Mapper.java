package ru.ershov.backend.mapper;


import ru.ershov.backend.dto.AbstractDto;
import ru.ershov.backend.entity.AbstractEntity;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {

    D updateDto(D updated, D recent);

    D updateDto(D updated, E recent);

    E updateEntity(E updated, D recent);

    E updateEntity(E updated, E recent);

    E toEntity(D dto);

    D toDto(E entity);
}
