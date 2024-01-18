package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;
import tr.com.metea.hotelium.domain.AccountTransaction;
import tr.com.metea.hotelium.util.SearchCriteria;
import tr.com.metea.hotelium.util.SearchCriteriaOptions;

import java.util.Date;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Getter
@Setter
public class AccountTransactionSearchCriteriaDTO extends AccountTransactionReadDTO implements BaseSearchCriteriaDTO<AccountTransaction> {
    private Date firstDate;
    private Date lastDate;

    public SearchCriteriaOptions<AccountTransaction> criteriaFieldMapper() {
        final var searchCriteriaOptions = new SearchCriteriaOptions<AccountTransaction>();
        searchCriteriaOptions.add(new SearchCriteria("id", this.getId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("type", this.getType(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("amount", this.getAmount(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("source", this.getSource(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("description", this.getDescription(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("creDate", this.getFirstDate(), SearchCriteria.SearchOperation.GREATER_THAN_EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("creDate", this.getLastDate(), SearchCriteria.SearchOperation.LESS_THAN_EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("orgId", this.getOrgId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("creUser", this.getCreUser(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("updUser", this.getUpdUser(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("nameTitle", this.getNameTitle(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("legalId", this.getLegalId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("draweeId", this.getDraweeId(), SearchCriteria.SearchOperation.EQUAL));

        return searchCriteriaOptions;
    }
}
