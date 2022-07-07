package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
@Getter
@Setter
public class SkuReadDTO extends SkuWriteDTO {
    private String id;
    private String orgId;
    private Date creDate;
    private String creUser;
    private Date updDate;
    private String updUser;
}
