package ru.ershov.backend.mapper;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ershov.backend.config.ModelMapperConfig;
import ru.ershov.backend.dto.AbstractDto;
import ru.ershov.backend.entity.AbstractEntity;

import java.util.Objects;

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
//        mapper.createTypeMap(entityClass, dtoClass)
//                .addMappings(m -> m.skip(AbstractDto::setCheckStatus))
//                .setPostConverter(context -> {
//                    E source = context.getSource();
//                    D destination = context.getDestination();
//                    destination.setCheckStatus(Status.valueOf(source.getCheckStatus()));
//                    return context.getDestination();
//                });
//        mapper.createTypeMap(dtoClass, entityClass)
//                .addMappings(m -> m.skip(AbstractEntity::setCheckStatus))
//                .setPostConverter(context -> {
//                    D source = context.getSource();
//                    E destination = context.getDestination();
//                    destination.setCheckStatus(source.getCheckStatus().name());
//                    return context.getDestination();
//                });
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
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }


}
