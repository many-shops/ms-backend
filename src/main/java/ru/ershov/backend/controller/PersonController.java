package ru.ershov.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ershov.backend.entity.Person;
import ru.ershov.backend.repository.PersonRepository;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

    @GetMapping
    List<Person> getPersons() {
        return personRepository.findAll();
    }
}
