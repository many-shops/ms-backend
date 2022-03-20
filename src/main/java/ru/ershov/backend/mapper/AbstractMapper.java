package ru.ershov.backend.mapper;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import ru.ershov.backend.dto.AbstractDto;
import ru.ershov.backend.entity.AbstractEntity;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

public abstract class AbstractMapper<E extends AbstractEntity, D extends AbstractDto> implements Mapper<E, D> {

    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    @Getter
    private final ModelMapper mapper;

    protected AbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
    }

    protected AbstractMapper(Class<E> entityClass, Class<D> dtoClass, ModelMapper mapper) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        this.mapper = mapper;
    }

    @Override
    public D updateDto(D updated, D recent) {
        mapper.map(recent, updated);
        return updated;
    }

    @Override
    public D updateDto(D updated, E recent) {
        mapper.map(recent, updated);
        return updated;
    }

    @Override
    public E updateEntity(E updated, D recent) {
        mapper.map(recent, updated);
        return updated;
    }

    @Override
    public E updateEntity(E updated, E recent) {
        mapper.map(recent, updated);
        return updated;
    }

    @Override
    public E toEntity(D dto) {
        return isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public List<E> toEntities(List<D> dtos) {
        return dtos.stream()
                .map(d -> isNull(d) ? null : mapper.map(d, entityClass))
                .collect(Collectors.toList());
    }

    @Override
    public D toDto(E entity) {
        return isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }

    @Override
    public List<D> toDtos(List<E> entities) {
        return entities.stream()
                .map(e -> isNull(e) ? null : mapper.map(e, dtoClass))
                .collect(Collectors.toList());
    }

}
