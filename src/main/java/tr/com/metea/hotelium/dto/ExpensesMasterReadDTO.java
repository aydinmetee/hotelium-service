package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;
import tr.com.metea.hotelium.domain.ExpensesMaster;

import java.util.Date;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
@Getter
@Setter
public class ExpensesMasterReadDTO extends ExpensesMasterWriteDTO {
    private String id;
    private ExpensesMaster.ExpensesStatus status;
    private String orgId;
    private Date creDate;
    private String creUser;
    private Date updDate;
    private String updUser;
}
