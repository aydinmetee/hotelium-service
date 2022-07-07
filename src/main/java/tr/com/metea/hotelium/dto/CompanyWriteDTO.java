package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class CompanyWriteDTO {
    private String nameTitle;
    private String address;
    private String legalNo;
    private String taxOffice;
    private Boolean valid;
    private String countryId;
    private String cityId;
    private String townId;
}
