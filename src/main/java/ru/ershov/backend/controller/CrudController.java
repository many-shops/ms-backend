package ru.ershov.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ershov.backend.dto.AbstractDto;
import ru.ershov.backend.entity.AbstractEntity;
import ru.ershov.backend.service.CrudService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
public class CrudController<E extends AbstractEntity, D extends AbstractDto> {

    protected final CrudService<E, D> service;

    @GetMapping
    @Operation(description = "Отдает массив объектов")
    public List<D> findAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(description = "Отдает объект по ID")
    public D findById(
            @PathVariable("id") Long id
    ) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(description = "Обновляет объект")
    public D insert(
            @PathVariable("id") Long id,
            @Valid @RequestBody D entity
    ) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Добавляет объект")
    public void delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
    }
}
