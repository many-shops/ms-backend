package ru.ershov.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ershov.backend.dto.PersonDto;
import ru.ershov.backend.entity.Person;
import ru.ershov.backend.repository.PersonRepository;

@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;

    public PersonDto getPerson(String username) {
        var personByUsername = personRepository.getPersonByUsername(username).orElseThrow();
        PersonDto personDto = new PersonDto();
        personDto.setUsername(personByUsername.getUsername());
        personDto.setId(personByUsername.getId());
        personDto.setEmail(personByUsername.getEmail());
        return personDto;
    }

    public PersonDto insert(PersonDto personDto) {
        var person = new Person();
        person.setEmail(personDto.getEmail());
        person.setUsername(personDto.getUsername());
        person.setPassword(personDto.getPassword());
        personRepository.save(person);
        return personDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.getPersonByUsername(username).orElseThrow();
    }
}
