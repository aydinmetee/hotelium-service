package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mete Aydin
 * @since 23.06.2022
 */
@Getter
@Setter
public class CurrencyReadDTO extends CurrencyWriteDTO {
    private String id;
}
