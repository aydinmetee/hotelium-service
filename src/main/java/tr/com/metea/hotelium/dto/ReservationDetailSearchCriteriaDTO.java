package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;
import tr.com.metea.hotelium.domain.ReservationDetail;
import tr.com.metea.hotelium.util.SearchCriteria;
import tr.com.metea.hotelium.util.SearchCriteriaOptions;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Getter
@Setter
public class ReservationDetailSearchCriteriaDTO extends ReservationDetailReadDTO implements BaseSearchCriteriaDTO<ReservationDetail> {
    public SearchCriteriaOptions<ReservationDetail> criteriaFieldMapper() {
        final var searchCriteriaOptions = new SearchCriteriaOptions<ReservationDetail>();
        searchCriteriaOptions.add(new SearchCriteria("id", this.getId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("customerFullName", this.getCustomerFullName(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("reservationMaster/id", this.getReservationMasterId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("customer/id", this.getCustomerId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("customer/phone", this.getCustomerPhone(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("customer/legalId", this.getCustomerLegalId(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("orgId",this.getOrgId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("creUser",this.getCreUser(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("updUser",this.getUpdUser(), SearchCriteria.SearchOperation.EQUAL));

        return searchCriteriaOptions;
    }
}
