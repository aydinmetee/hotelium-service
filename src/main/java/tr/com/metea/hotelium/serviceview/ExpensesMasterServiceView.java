package tr.com.metea.hotelium.serviceview;

import tr.com.metea.hotelium.domain.ExpensesMaster;
import tr.com.metea.hotelium.dto.ExpensesMasterReadDTO;
import tr.com.metea.hotelium.dto.ExpensesMasterSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.ExpensesMasterWriteDTO;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
public interface ExpensesMasterServiceView extends BaseServiceView<ExpensesMaster, ExpensesMasterWriteDTO, ExpensesMasterReadDTO, ExpensesMasterSearchCriteriaDTO>{
}
