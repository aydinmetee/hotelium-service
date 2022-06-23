package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class CustomerReadDTO extends CustomerWriteDTO {
    private String companyName;
    private String id;
    private String orgId;
    private Date creDate;
    private String creUser;
    private Date updDate;
    private String updUser;
    private String companyId;
}
