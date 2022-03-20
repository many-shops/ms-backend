package ru.ershov.backend.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ShoppingCartDto extends AbstractDto {
    @Min(0)
    private Long amount;
    @NotNull
    private Long itemId;
}
