package tr.com.metea.hotelium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.ExpensesDetail;
import tr.com.metea.hotelium.dto.ExpensesDetailSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.ExpensesDetailWriteDTO;
import tr.com.metea.hotelium.service.ExpensesDetailService;
import tr.com.metea.hotelium.service.ExpensesMasterService;
import tr.com.metea.hotelium.service.SkuService;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
@Service
@RequiredArgsConstructor
public class ExpensesDetailServiceImpl extends BaseServiceImpl<ExpensesDetail, ExpensesDetailWriteDTO, ExpensesDetailSearchCriteriaDTO>
        implements ExpensesDetailService {

    private final ExpensesMasterService expensesMasterService;
    private final SkuService skuService;

    @Override
    public ExpensesDetail convertToEntity(ExpensesDetailWriteDTO dto) {
        return modelMapper.map(dto, ExpensesDetail.class);
    }

    @Override
    public ExpensesDetail mapDtoToEntity(ExpensesDetail expensesDetail, ExpensesDetailWriteDTO dto) {
        expensesDetail.setExpensesMaster(expensesMasterService.getById(dto.getExpensesMasterId()));
        expensesDetail.setSku(skuService.getById(dto.getSkuId()));
        expensesDetail.setPrice(dto.getPrice());
        expensesDetail.setQuantity(dto.getQuantity());
        return expensesDetail;
    }
}
