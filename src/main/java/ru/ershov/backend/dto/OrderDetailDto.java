package ru.ershov.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class OrderDetailDto extends AbstractDto {

    private Long amount;
    private Long itemId;
    private Long orderId;
}
