package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author Mete Aydin
 * @since 22.03.2022
 */
@Getter
@Setter
@NoArgsConstructor
public class ResarvationTransactionReadDTO {
    private String id;
    private Date resarvationDate;
    private String roomCode;
}
