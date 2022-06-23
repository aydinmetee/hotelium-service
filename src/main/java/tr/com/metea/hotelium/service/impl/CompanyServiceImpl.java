package tr.com.metea.hotelium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.Company;
import tr.com.metea.hotelium.dto.CompanySearchCriteriaDTO;
import tr.com.metea.hotelium.dto.CompanyWriteDTO;
import tr.com.metea.hotelium.repository.CompanyRepository;
import tr.com.metea.hotelium.service.CompanyService;

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

    @Override
    public Company convertToEntity(CompanyWriteDTO dto) {
        return modelMapper.map(dto, Company.class);
    }

    @Override
    public Company mapDtoToEntity(Company entity, CompanyWriteDTO dto) {
        entity.setAddress(dto.getAddress());
        entity.setNameTitle(dto.getNameTitle());
        entity.setLegalNo(dto.getLegalNo());
        entity.setTaxOffice(dto.getTaxOffice());
        return entity;
    }
}
