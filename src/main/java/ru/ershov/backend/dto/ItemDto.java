package ru.ershov.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDto extends AbstractDto {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @DecimalMin(value = "0.01", inclusive = false)
    @Digits(integer=8, fraction=2)
    private BigDecimal price;

    private Long companyId;

    // field only for cart
    @Null
    private Long amount;
}
