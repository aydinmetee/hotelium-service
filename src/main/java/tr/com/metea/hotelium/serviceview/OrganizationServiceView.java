package tr.com.metea.hotelium.serviceview;

import tr.com.metea.hotelium.dto.OrganizationModelDTO;

import java.util.List;

/**
 * @author Mete Aydin
 * @since 18.03.2022
 */
public interface OrganizationServiceView {
    OrganizationModelDTO create(OrganizationModelDTO organizationModelDTO);

    List<OrganizationModelDTO> getAll();
}
