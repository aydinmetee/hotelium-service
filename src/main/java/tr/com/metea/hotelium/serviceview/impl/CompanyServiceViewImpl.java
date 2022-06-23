package tr.com.metea.hotelium.serviceview.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.Company;
import tr.com.metea.hotelium.dto.CompanyReadDTO;
import tr.com.metea.hotelium.dto.CompanySearchCriteriaDTO;
import tr.com.metea.hotelium.dto.CompanyWriteDTO;
import tr.com.metea.hotelium.service.CompanyService;
import tr.com.metea.hotelium.serviceview.CompanyServiceView;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@RequiredArgsConstructor
@Service
public class CompanyServiceViewImpl extends
        BaseServiceViewImpl<Company, CompanyWriteDTO, CompanyReadDTO, CompanySearchCriteriaDTO>
        implements CompanyServiceView {
    private final CompanyService companyService;

    @Override
    public CompanyReadDTO convertToDTO(Company company) {
        return modelMapper.map(company, CompanyReadDTO.class);
    }
}
