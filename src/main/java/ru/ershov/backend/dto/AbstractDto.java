package ru.ershov.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Null;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Validated
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class AbstractDto implements Serializable {
    @Null // we don't change id
    @JsonProperty("id")
    @EqualsAndHashCode.Include
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Null
    @JsonProperty("updated_time")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Timestamp updatedTime;

    @Null
    @JsonProperty("created_time")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Timestamp createdTime;
}
