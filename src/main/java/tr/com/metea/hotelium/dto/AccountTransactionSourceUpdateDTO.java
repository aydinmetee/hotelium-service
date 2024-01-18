package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;
import tr.com.metea.hotelium.domain.AccountTransaction;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class AccountTransactionSourceUpdateDTO {
    @NotNull
    private String id;
    @NotNull
    private AccountTransaction.TransactionSource source;

}
