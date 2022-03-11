package ru.ershov.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.ershov.backend.config.security.TokenProvider;
import ru.ershov.backend.dto.PersonDto;
import ru.ershov.backend.dto.RegistrationRequest;
import ru.ershov.backend.dto.TokenDto;
import ru.ershov.backend.entity.Person;
import ru.ershov.backend.service.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider jwtTokenUtil;
    private final PersonService userService;

    @PostMapping("/signup")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        PersonDto p = new PersonDto();
        p.setPassword(registrationRequest.getPassword());
        p.setEmail(registrationRequest.getLogin());
        p.setEnabled(true);
        userService.insert(p);
        return "OK";
    }

    @PostMapping(value = "/signin")
    public TokenDto generateToken(@RequestBody RegistrationRequest loginPerson) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginPerson.getLogin(),
                        loginPerson.getPassword()
                )
        );
        Person principal = (Person) authentication.getPrincipal();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        System.out.println(roles);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        PersonDto user = userService.getPerson(principal.getUsername());
        System.out.println(user.getId());
        return new TokenDto(jwtTokenUtil.generateToken(authentication), user.getId(), user.getUsername(),
                user.getEmail(), roles);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value="/userping") // test
    public String userPing(){
        return "Any User Can Read This";
    }
}
