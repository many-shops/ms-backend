package ru.ershov.backend.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ItemDto extends AbstractDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @DecimalMin(value = "0.01", inclusive = false)
    @Digits(integer=8, fraction=2)
    private BigDecimal price;

//    @JsonIgnoreProperties({"items"})
    private Long companyId;

    // field only for cart
    @Null
    private Long amount;
}
