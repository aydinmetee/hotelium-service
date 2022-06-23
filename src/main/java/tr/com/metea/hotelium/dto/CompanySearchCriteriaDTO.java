package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;
import tr.com.metea.hotelium.domain.Company;
import tr.com.metea.hotelium.util.SearchCriteria;
import tr.com.metea.hotelium.util.SearchCriteriaOptions;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@Getter
@Setter
public class CompanySearchCriteriaDTO extends CompanyReadDTO implements BaseSearchCriteriaDTO<Company> {
    public SearchCriteriaOptions<Company> criteriaFieldMapper() {
        final var searchCriteriaOptions = new SearchCriteriaOptions<Company>();
        searchCriteriaOptions.add(new SearchCriteria("id", this.getId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("nameTitle", this.getNameTitle(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("address", this.getAddress(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("legalNo", this.getLegalNo(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("taxOffice", this.getTaxOffice(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("orgId", this.getOrgId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("creUser", this.getCreUser(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("updUser", this.getUpdUser(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("valid", this.getValid(), SearchCriteria.SearchOperation.EQUAL));

        return searchCriteriaOptions;
    }
}
