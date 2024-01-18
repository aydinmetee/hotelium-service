package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Getter
@Setter
public class CustomerWriteDTO {
    private String name;
    private String lastname;
    private String legalId;
    private String phone;
    private Boolean valid;
}
