package tr.com.metea.hotelium.dto;

import tr.com.metea.hotelium.util.SearchCriteriaOptions;

/**
 * @author Mete Aydin
 * @since 7.06.2022
 */

public interface BaseSearchCriteriaDTO<E> {
    SearchCriteriaOptions<E> criteriaFieldMapper();
}
