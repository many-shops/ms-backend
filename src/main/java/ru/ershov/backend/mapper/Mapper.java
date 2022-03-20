package ru.ershov.backend.mapper;


import ru.ershov.backend.dto.AbstractDto;
import ru.ershov.backend.entity.AbstractEntity;

import java.util.List;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {

    D updateDto(D updated, D recent);

    D updateDto(D updated, E recent);

    E updateEntity(E updated, D recent);

    E updateEntity(E updated, E recent);

    E toEntity(D dto);

    List<E> toEntities(List<D> dtos);

    D toDto(E entity);

    List<D> toDtos(List<E> entities);
}
