package ru.ershov.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ershov.backend.dto.PersonDto;
import ru.ershov.backend.entity.Person;
import ru.ershov.backend.mapper.AbstractMapper;
import ru.ershov.backend.repository.PersonRepository;
import ru.ershov.backend.repository.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {

    private final AbstractMapper<Person, PersonDto> mapper;
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    public PersonDto getPerson(String username) {
        var entity = personRepository.getPersonByUsername(username).orElseThrow();
//        entity.setPassword("");
        return mapper.toDto(entity);
    }

    public PersonDto getById(Long id) {
        return mapper.toDto(personRepository.findById(id).orElseThrow());
    }

    private Person getByIdInternal(Long id) {
        return personRepository.findById(id).orElseThrow();
    }

    public List<PersonDto> getAll() {
        return personRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public PersonDto insert(PersonDto personDto) {
        Person s = mapper.toEntity(personDto);
        s.getRoles().add(roleRepository.findByName("ROLE_USER").orElseThrow());
        return mapper.toDto(
                personRepository.save(s)
        );
    }

    public PersonDto update(Long id, PersonDto personDto) {
        var person = personRepository.findById(id).orElseThrow();
        mapper.updateEntity(person, personDto);
        return mapper.toDto(personRepository.save(person));
    }

    public void delete(Long id) {
        personRepository.delete(getByIdInternal(id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.getPersonByUsername(username).orElseThrow();
    }
}
