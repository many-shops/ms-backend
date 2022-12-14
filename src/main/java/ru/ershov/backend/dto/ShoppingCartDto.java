package ru.ershov.backend.dto;

import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ShoppingCartDto extends AbstractDto {
    @Min(0)
    private Long amount;
    @Min(0)
    private Long itemId;
}
