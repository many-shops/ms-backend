package ru.ershov.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.ershov.backend.dto.PersonDto;
import ru.ershov.backend.entity.Person;
import ru.ershov.backend.service.PersonService;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public List<PersonDto> getAll() {
        return personService.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/{person_id}")
    public PersonDto getById(@PathVariable("person_id") Long id) {
        return personService.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{person_id}")
    public ResponseEntity<Object> deleteById(@PathVariable("person_id") Long id) {
        personService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public PersonDto getCurrentSessionPerson(@AuthenticationPrincipal Person person) throws NoSuchElementException {
        return personService.getById(person.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public PersonDto getCurrentSessionPerson(@RequestBody PersonDto personDto, @AuthenticationPrincipal Person person) {
        return personService.update(person.getId(), personDto);
    }
}
