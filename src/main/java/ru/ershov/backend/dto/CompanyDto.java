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
public class CompanyDto extends AbstractDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    private Long adminId;
//    @Null
//    @JsonIgnoreProperties({"company"})
//    private List<ItemDto> items;
}
