package ru.ershov.backend.dto;


import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class PersonDto extends AbstractDto {
    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String vkLink;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private Boolean enabled;
}
