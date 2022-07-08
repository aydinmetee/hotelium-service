package tr.com.metea.hotelium.service;

import tr.com.metea.hotelium.domain.ExpensesMaster;
import tr.com.metea.hotelium.dto.ExpensesMasterSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.ExpensesMasterWriteDTO;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
public interface ExpensesMasterService
        extends BaseService<ExpensesMaster, ExpensesMasterWriteDTO, ExpensesMasterSearchCriteriaDTO> {
}
