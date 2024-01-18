package tr.com.metea.hotelium.serviceview;

import tr.com.metea.hotelium.domain.ReservationDetail;
import tr.com.metea.hotelium.dto.ReservationDetailReadDTO;
import tr.com.metea.hotelium.dto.ReservationDetailSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.ReservationDetailWriteDTO;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface ReservationDetailServiceView
        extends BaseServiceView<ReservationDetail, ReservationDetailWriteDTO, ReservationDetailReadDTO, ReservationDetailSearchCriteriaDTO> {
}
