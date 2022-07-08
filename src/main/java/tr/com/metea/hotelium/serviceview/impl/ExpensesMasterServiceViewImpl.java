package tr.com.metea.hotelium.serviceview.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.ExpensesMaster;
import tr.com.metea.hotelium.dto.ExpensesMasterReadDTO;
import tr.com.metea.hotelium.dto.ExpensesMasterSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.ExpensesMasterWriteDTO;
import tr.com.metea.hotelium.serviceview.ExpensesMasterServiceView;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
@Service
@RequiredArgsConstructor
public class ExpensesMasterServiceViewImpl extends BaseServiceViewImpl<ExpensesMaster, ExpensesMasterWriteDTO, ExpensesMasterReadDTO, ExpensesMasterSearchCriteriaDTO>
        implements ExpensesMasterServiceView {
    @Override
    public ExpensesMasterReadDTO convertToDTO(ExpensesMaster expensesMaster) {
        final var expensesMasterDTO = modelMapper.map(expensesMaster, ExpensesMasterReadDTO.class);
        expensesMasterDTO.setReservationMasterId(expensesMaster.getReservationMaster().getId());
        return expensesMasterDTO;
    }
}
