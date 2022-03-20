package ru.ershov.backend.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode
@Data
@SuperBuilder
@NoArgsConstructor
public class ErrorResponseDto {
    private String type;
    private String message;
    private String url;
}
