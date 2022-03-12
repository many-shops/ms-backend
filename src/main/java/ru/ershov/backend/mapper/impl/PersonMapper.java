package ru.ershov.backend.mapper.impl;

import org.springframework.stereotype.Component;
import ru.ershov.backend.dto.PersonDto;
import ru.ershov.backend.entity.Person;
import ru.ershov.backend.mapper.AbstractMapper;

@Component
public class PersonMapper extends AbstractMapper<Person, PersonDto> {
    PersonMapper() {
        super(Person.class, PersonDto.class);
    }
}
