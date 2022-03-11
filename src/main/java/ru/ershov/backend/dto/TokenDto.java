package ru.ershov.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TokenDto {

    private String accessToken;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

}
