package ru.ershov.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import ru.ershov.backend.entity.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @Null
    @JsonIgnoreProperties({"persons"})
    private Set<Role> roles;
}
