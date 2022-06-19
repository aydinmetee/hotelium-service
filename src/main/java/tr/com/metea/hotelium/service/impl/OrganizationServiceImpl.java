package tr.com.metea.hotelium.service.impl;

import tr.com.metea.hotelium.domain.Organization;
import tr.com.metea.hotelium.dto.OrganizationModelDTO;
import tr.com.metea.hotelium.exception.ServiceExecutionException;
import tr.com.metea.hotelium.repository.OrganizationRepository;
import tr.com.metea.hotelium.service.OrganizationService;
import tr.com.metea.hotelium.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @author Mete Aydin
 * @since 18.03.2022
 */
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final MessageUtil messageUtil;
    private final ModelMapper modelMapper;

    @Override
    public Organization create(OrganizationModelDTO organizationModelDTO) {
        final var existOrgList = organizationRepository.findAllByCode(organizationModelDTO.getCode());
        if (!existOrgList.isEmpty()) {
            throw new ServiceExecutionException(messageUtil.get("validation.orgDef.codeAlreadyExist"));
        }
        final var orgDb = modelMapper.map(organizationModelDTO, Organization.class);
        return organizationRepository.save(orgDb);
    }
}
