package tr.com.metea.hotelium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.Currency;
import tr.com.metea.hotelium.dto.CurrencySearchCriteriaDTO;
import tr.com.metea.hotelium.repository.CurrencyRepository;
import tr.com.metea.hotelium.service.CurrencyService;

/**
 * @author Mete Aydin
 * @since 23.06.2022
 */
@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;

    @Override
    public Page<Currency> search(CurrencySearchCriteriaDTO filter, Pageable pageable) {
        return currencyRepository.findAll(filter.criteriaFieldMapper(), pageable);
    }
}
