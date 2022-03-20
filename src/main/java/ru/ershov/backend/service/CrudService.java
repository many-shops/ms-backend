package ru.ershov.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ershov.backend.dto.AbstractDto;
import ru.ershov.backend.entity.AbstractEntity;
import ru.ershov.backend.mapper.AbstractMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class CrudService<E extends AbstractEntity, D extends AbstractDto> {

    private final AbstractMapper<E, D> mapper;
    private final JpaRepository<E, Long> repository;

    public D getById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    protected E getByIdInternal(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<D> getAll() {
        return mapper.toDtos(repository.findAll());
    }

    public D insert(D dto) {
        return insert(mapper.toEntity(dto));
    }

    protected D insert(E dto) {
        return mapper.toDto(repository.save(dto));
    }

    public D update(Long id, D dto) {
        var entity = repository.findById(id).orElseThrow();
        mapper.updateEntity(entity, dto);
        return mapper.toDto(repository.save(entity));
    }

    public boolean existById(Long id) {
        return repository.existsById(id);
    }

    public void delete(Long id) {
        repository.delete(getByIdInternal(id));
    }


}
