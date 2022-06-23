package tr.com.metea.hotelium.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tr.com.metea.hotelium.domain.Currency;
import tr.com.metea.hotelium.dto.CurrencySearchCriteriaDTO;

/**
 * @author Mete Aydin
 * @since 23.06.2022
 */
public interface CurrencyService {
    Page<Currency> search(CurrencySearchCriteriaDTO filter, Pageable pageable);
}
