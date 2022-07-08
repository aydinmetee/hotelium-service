package tr.com.metea.hotelium.dto;

import tr.com.metea.hotelium.domain.ExpensesDetail;
import tr.com.metea.hotelium.domain.ExpensesMaster;
import tr.com.metea.hotelium.util.SearchCriteria;
import tr.com.metea.hotelium.util.SearchCriteriaOptions;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
public class ExpensesDetailSearchCriteriaDTO extends ExpensesDetailReadDTO
        implements BaseSearchCriteriaDTO<ExpensesDetail> {
    @Override
    public SearchCriteriaOptions criteriaFieldMapper() {
        final var searchCriteriaOptions = new SearchCriteriaOptions<ExpensesMaster>();
        searchCriteriaOptions.add(new SearchCriteria("id", this.getId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("price", this.getPrice(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("orgId", this.getOrgId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("creUser", this.getCreUser(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("updUser", this.getUpdUser(), SearchCriteria.SearchOperation.EQUAL));

        return searchCriteriaOptions;
    }
}
