package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;
import tr.com.metea.hotelium.domain.ReservationMaster;
import tr.com.metea.hotelium.util.SearchCriteria;
import tr.com.metea.hotelium.util.SearchCriteriaOptions;

import java.util.Date;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Getter
@Setter
public class ReservationMasterSearchCriteriaDTO extends ReservationMasterReadDTO implements BaseSearchCriteriaDTO<ReservationMaster> {
    private Date firstDate;
    private Date lastDate;

    public SearchCriteriaOptions<ReservationMaster> criteriaFieldMapper() {
        final var searchCriteriaOptions = new SearchCriteriaOptions<ReservationMaster>();
        searchCriteriaOptions.add(new SearchCriteria("id", this.getId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("status", this.getStatus(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("room/id", this.getRoomId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("room/code", this.getRoomCode(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("checkInDate", this.getCheckInDate(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("checkOutDate", this.getCheckOutDate(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("description", this.getDescription(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("orgId", this.getOrgId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("creUser", this.getCreUser(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("updUser", this.getUpdUser(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("reservationDate", this.getReservationDate(), SearchCriteria.SearchOperation.GREATER_THAN_EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("reservationDate", this.getReservationDate(), SearchCriteria.SearchOperation.LESS_THAN_EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("duration", this.getDuration(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("dailyAmount", this.getDailyAmount(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("reservationNo", this.getReservationNo(), SearchCriteria.SearchOperation.LIKE));

        return searchCriteriaOptions;
    }
}
