package tr.com.metea.hotelium.serviceview.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.ExpensesDetail;
import tr.com.metea.hotelium.dto.ExpensesDetailReadDTO;
import tr.com.metea.hotelium.dto.ExpensesDetailSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.ExpensesDetailWriteDTO;
import tr.com.metea.hotelium.serviceview.ExpensesDetailServiceView;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
@Service
@RequiredArgsConstructor
public class ExpensesDetailServiceViewImpl extends BaseServiceViewImpl<ExpensesDetail, ExpensesDetailWriteDTO, ExpensesDetailReadDTO, ExpensesDetailSearchCriteriaDTO>
        implements ExpensesDetailServiceView {
    @Override
    public ExpensesDetailReadDTO convertToDTO(ExpensesDetail expensesDetail) {
        final var dto = modelMapper.map(expensesDetail, ExpensesDetailReadDTO.class);
        dto.setSkuName(expensesDetail.getSku().getName());
        return dto;
    }
}
