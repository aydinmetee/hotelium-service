package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Getter
@Setter
public class RoomWriteDTO {
    @NotBlank
    private String code;
    private Long capacity;
    private Boolean valid;
}
