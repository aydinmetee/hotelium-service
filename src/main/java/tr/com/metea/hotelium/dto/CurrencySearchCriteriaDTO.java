package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;
import tr.com.metea.hotelium.domain.Currency;
import tr.com.metea.hotelium.util.SearchCriteria;
import tr.com.metea.hotelium.util.SearchCriteriaOptions;

/**
 * @author Mete Aydin
 * @since 23.06.2022
 */
@Getter
@Setter
public class CurrencySearchCriteriaDTO extends CurrencyReadDTO {

    public SearchCriteriaOptions<Currency> criteriaFieldMapper() {
        final var searchCriteriaOptions = new SearchCriteriaOptions<Currency>();
        searchCriteriaOptions.add(new SearchCriteria("id", this.getId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("name", this.getName(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("code", this.getCode(), SearchCriteria.SearchOperation.LIKE));

        return searchCriteriaOptions;
    }

}
