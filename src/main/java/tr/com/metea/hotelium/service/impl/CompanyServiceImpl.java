package tr.com.metea.hotelium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.Company;
import tr.com.metea.hotelium.dto.CompanySearchCriteriaDTO;
import tr.com.metea.hotelium.dto.CompanyWriteDTO;
import tr.com.metea.hotelium.repository.CompanyRepository;
import tr.com.metea.hotelium.service.CompanyService;
import tr.com.metea.hotelium.service.LocationService;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl extends
        BaseServiceImpl<Company, CompanyWriteDTO, CompanySearchCriteriaDTO>
        implements CompanyService {
    private final CompanyRepository companyRepository;
    private final LocationService locationService;

    @Override
    public Company convertToEntity(CompanyWriteDTO dto) {
        final var entity = modelMapper.map(dto, Company.class);
        entity.setCity(locationService.getCityById(dto.getCityId()));
        entity.setCountry(locationService.getCountryById(dto.getCountryId()));
        entity.setTown(locationService.getTownById(dto.getTownId()));
        return entity;
    }

    @Override
    public Company mapDtoToEntity(Company entity, CompanyWriteDTO dto) {
        entity.setAddress(dto.getAddress());
        entity.setNameTitle(dto.getNameTitle());
        entity.setLegalNo(dto.getLegalNo());
        entity.setTaxOffice(dto.getTaxOffice());
        entity.setCity(locationService.getCityById(dto.getCityId()));
        entity.setCountry(locationService.getCountryById(dto.getCountryId()));
        entity.setTown(locationService.getTownById(dto.getTownId()));
        return entity;
    }
}
