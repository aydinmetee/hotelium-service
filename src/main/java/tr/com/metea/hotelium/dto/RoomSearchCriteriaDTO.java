package tr.com.metea.hotelium.dto;

import lombok.Getter;
import lombok.Setter;
import tr.com.metea.hotelium.domain.Room;
import tr.com.metea.hotelium.util.SearchCriteria;
import tr.com.metea.hotelium.util.SearchCriteriaOptions;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Getter
@Setter
public class RoomSearchCriteriaDTO extends RoomReadDTO implements BaseSearchCriteriaDTO<Room> {
    public SearchCriteriaOptions<Room> criteriaFieldMapper() {
        final var searchCriteriaOptions = new SearchCriteriaOptions<Room>();
        searchCriteriaOptions.add(new SearchCriteria("id", this.getId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("status", this.getStatus(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("code", this.getCode(), SearchCriteria.SearchOperation.LIKE));
        searchCriteriaOptions.add(new SearchCriteria("capacity", this.getCapacity(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("orgId", this.getOrgId(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("creUser", this.getCreUser(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("updUser", this.getUpdUser(), SearchCriteria.SearchOperation.EQUAL));
        searchCriteriaOptions.add(new SearchCriteria("valid", this.getValid(), SearchCriteria.SearchOperation.EQUAL));

        return searchCriteriaOptions;
    }
}
