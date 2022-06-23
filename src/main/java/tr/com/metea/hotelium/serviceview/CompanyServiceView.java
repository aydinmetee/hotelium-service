package tr.com.metea.hotelium.serviceview;

import tr.com.metea.hotelium.domain.Company;
import tr.com.metea.hotelium.dto.CompanyReadDTO;
import tr.com.metea.hotelium.dto.CompanySearchCriteriaDTO;
import tr.com.metea.hotelium.dto.CompanyWriteDTO;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface CompanyServiceView extends
        BaseServiceView<Company, CompanyWriteDTO, CompanyReadDTO, CompanySearchCriteriaDTO> {

}
