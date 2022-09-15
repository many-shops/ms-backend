package ru.ershov.backend.dto;

import lombok.*;

import javax.validation.constraints.Null;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class OrderDto extends AbstractDto {
    private String address;
    private String status;
    @Null
    private Long personId;
    @Null
    private List<OrderDetailDto> orderDetails;
}
