package tr.com.metea.hotelium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tr.com.metea.hotelium.domain.ExpensesMaster;
import tr.com.metea.hotelium.dto.ExpensesMasterSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.ExpensesMasterWriteDTO;
import tr.com.metea.hotelium.exception.ServiceExecutionException;
import tr.com.metea.hotelium.service.ExpensesMasterService;
import tr.com.metea.hotelium.service.ReservationMasterService;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
@Service
@RequiredArgsConstructor
public class ExpensesMasterServiceImpl extends BaseServiceImpl<ExpensesMaster, ExpensesMasterWriteDTO, ExpensesMasterSearchCriteriaDTO>
        implements ExpensesMasterService {
    private final ReservationMasterService reservationMasterService;

    @Override
    public ExpensesMaster create(ExpensesMasterWriteDTO dto) {
        checkReservationIsUnique(dto.getReservationMasterId());
        final var expenses = convertToEntity(dto);
        expenses.setStatus(ExpensesMaster.ExpensesStatus.UNPAID);
        if(Objects.isNull(dto.getAmount())){
            expenses.setAmount(BigDecimal.ZERO);
        }
        return repository.save(expenses);
    }

    @Override
    public ExpensesMaster update(String id, ExpensesMasterWriteDTO dto) {
        final var entity = getById(id);
        if (!StringUtils.isEmpty(dto.getReservationMasterId()) &&
                !entity.getReservationMaster().getId().equals(dto.getReservationMasterId())) {
            checkReservationIsUnique(dto.getReservationMasterId());
        }
        return repository.save(mapDtoToEntity(entity, dto));
    }

    @Override
    public ExpensesMaster convertToEntity(ExpensesMasterWriteDTO dto) {
        final var entity = modelMapper.map(dto, ExpensesMaster.class);
        entity.setReservationMaster(reservationMasterService.getById(dto.getReservationMasterId()));
        return entity;
    }

    @Override
    public ExpensesMaster mapDtoToEntity(ExpensesMaster expensesMaster, ExpensesMasterWriteDTO dto) {
        expensesMaster.setReservationMaster(reservationMasterService.getById(dto.getReservationMasterId()));
        return expensesMaster;
    }

    private void checkReservationIsUnique(String reservationMasterId) {
        final var filter = new ExpensesMasterSearchCriteriaDTO();
        filter.setReservationMasterId(reservationMasterId);
        final var expenses = search(filter, PageRequest.of(0, 1));
        if (expenses.hasContent()) {
            throw new ServiceExecutionException(messageUtil.get("expensesMaster.reservation.not-unique"));
        }
    }
}
