package tr.com.metea.hotelium.serviceview.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.Organization;
import tr.com.metea.hotelium.dto.OrganizationModelDTO;
import tr.com.metea.hotelium.service.OrganizationService;
import tr.com.metea.hotelium.serviceview.OrganizationServiceView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mete Aydin
 * @since 18.03.2022
 */
@Service
@RequiredArgsConstructor
public class OrganizationServiceViewImpl implements OrganizationServiceView {
    private final OrganizationService organizationService;
    private final ModelMapper modelMapper;

    @Override
    public OrganizationModelDTO create(OrganizationModelDTO organizationModelDTO) {
        return convertToDTO(organizationService.create(organizationModelDTO));
    }

    @Override
    public List<OrganizationModelDTO> getAll() {
        return organizationService.getAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OrganizationModelDTO convertToDTO(Organization organization) {
        return modelMapper.map(organization, OrganizationModelDTO.class);
    }
}
