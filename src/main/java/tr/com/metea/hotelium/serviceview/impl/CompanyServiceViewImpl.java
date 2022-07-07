package tr.com.metea.hotelium.serviceview.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.Company;
import tr.com.metea.hotelium.dto.CompanyReadDTO;
import tr.com.metea.hotelium.dto.CompanySearchCriteriaDTO;
import tr.com.metea.hotelium.dto.CompanyWriteDTO;
import tr.com.metea.hotelium.service.CompanyService;
import tr.com.metea.hotelium.serviceview.CompanyServiceView;

import java.util.Objects;

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
        final var companyReadDTO = modelMapper.map(company, CompanyReadDTO.class);

        if (Objects.nonNull(company.getCountry())) {
            companyReadDTO.setCountryName(company.getCountry().getName());
            companyReadDTO.setCountryId(company.getCountry().getId());
        }
        if (Objects.nonNull(company.getCity())) {
            companyReadDTO.setCityName(company.getCity().getName());
            companyReadDTO.setCityId(company.getCity().getId());
        }
        if (Objects.nonNull(company.getTown())) {
            companyReadDTO.setTownName(company.getTown().getName());
            companyReadDTO.setTownId(company.getTown().getId());
        }
        return companyReadDTO;
    }
}
