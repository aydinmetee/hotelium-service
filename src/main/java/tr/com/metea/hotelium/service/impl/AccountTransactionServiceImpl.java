package tr.com.metea.hotelium.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.metea.hotelium.domain.AccountTransaction;
import tr.com.metea.hotelium.dto.AccountTransactionSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.AccountTransactionSourceUpdateDTO;
import tr.com.metea.hotelium.dto.AccountTransactionWriteDTO;
import tr.com.metea.hotelium.repository.AccountTransactionRepository;
import tr.com.metea.hotelium.service.AccountTransactionService;
import tr.com.metea.hotelium.service.ReservationMasterService;
import tr.com.metea.hotelium.util.MessageUtil;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Service
public class AccountTransactionServiceImpl extends BaseServiceImpl<AccountTransaction, AccountTransactionWriteDTO, AccountTransactionSearchCriteriaDTO>
        implements AccountTransactionService {
    private final AccountTransactionRepository accountTransactionRepository;
    private final ReservationMasterService reservationMasterService;

    @Autowired
    public AccountTransactionServiceImpl(AccountTransactionRepository accountTransactionRepository,
                                         ModelMapper modelMapper,
                                         MessageUtil messageUtil,
                                         @Lazy ReservationMasterService reservationMasterService) {
        this.accountTransactionRepository = accountTransactionRepository;
        this.modelMapper = modelMapper;
        this.messageUtil = messageUtil;
        this.reservationMasterService = reservationMasterService;
    }


    @Override
    public AccountTransaction createIncome(AccountTransactionWriteDTO accountTransactionWriteDTO) {
        final var reservationMaster = reservationMasterService.getById(accountTransactionWriteDTO.getReservationMasterId());
        final var accountDB = modelMapper.map(accountTransactionWriteDTO, AccountTransaction.class);
        accountDB.setReservationMaster(reservationMaster);
        accountDB.setType(AccountTransaction.TransactionType.INCOME);
        return accountTransactionRepository.save(accountDB);
    }

    @Override
    public AccountTransaction createExpense(AccountTransactionWriteDTO accountTransactionWriteDTO) {
        final var accountDB = modelMapper.map(accountTransactionWriteDTO, AccountTransaction.class);
        accountDB.setReservationMaster(null);
        accountDB.setType(AccountTransaction.TransactionType.EXPENSE);
        return accountTransactionRepository.save(accountDB);
    }

    @Override
    @Transactional
    public void deleteForReservation(String resarvationMasterId) {
        final var filter = new AccountTransactionSearchCriteriaDTO();
        filter.setReservationMasterId(resarvationMasterId);
        final var deletedAccount = search(filter, PageRequest.of(0, 1)).getContent().get(0);
        accountTransactionRepository.deleteById(deletedAccount.getId());
    }


    @Override
    public AccountTransaction updateTransactionSource(AccountTransactionSourceUpdateDTO updateDTO) {
        final var acc = getById(updateDTO.getId());
        acc.setSource(updateDTO.getSource());
        return accountTransactionRepository.save(acc);
    }

    @Override
    public Boolean checkPayment(String reservationMasterId) {
        final var filter = new AccountTransactionSearchCriteriaDTO();
        filter.setReservationMasterId(reservationMasterId);
        final var result = search(filter, PageRequest.of(0, 1));
        if (result.hasContent()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    @Override
    public AccountTransaction convertToEntity(AccountTransactionWriteDTO dto) {
        return modelMapper.map(dto, AccountTransaction.class);
    }

    @Override
    public AccountTransaction mapDtoToEntity(AccountTransaction accountTransaction, AccountTransactionWriteDTO dto) {
        accountTransaction.setAmount(dto.getAmount());
        accountTransaction.setDrawee(dto.getDrawee());
        accountTransaction.setDraweeId(dto.getDraweeId());
        accountTransaction.setDescription(dto.getDescription());
        accountTransaction.setLegalId(dto.getLegalId());
        accountTransaction.setReservationMaster(reservationMasterService.getById(dto.getReservationMasterId()));
        accountTransaction.setNameTitle(dto.getNameTitle());
        accountTransaction.setSource(dto.getSource());
        return accountTransaction;
    }
}
