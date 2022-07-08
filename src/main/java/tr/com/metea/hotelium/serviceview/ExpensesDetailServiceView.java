package tr.com.metea.hotelium.serviceview;

import tr.com.metea.hotelium.domain.ExpensesDetail;
import tr.com.metea.hotelium.dto.ExpensesDetailReadDTO;
import tr.com.metea.hotelium.dto.ExpensesDetailSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.ExpensesDetailWriteDTO;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
public interface ExpensesDetailServiceView extends BaseServiceView<ExpensesDetail, ExpensesDetailWriteDTO, ExpensesDetailReadDTO, ExpensesDetailSearchCriteriaDTO>{
}
