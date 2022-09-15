package ru.ershov.backend.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequest {

    @Size(max = 100)
    @NotEmpty
    private String login;

    @Size(min = 6)
    @NotEmpty
    private String password;
}
