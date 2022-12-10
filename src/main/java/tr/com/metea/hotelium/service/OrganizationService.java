package tr.com.metea.hotelium.service;

import tr.com.metea.hotelium.domain.Organization;
import tr.com.metea.hotelium.dto.OrganizationModelDTO;

import java.util.List;

/**
 * @author Mete Aydin
 * @since 18.03.2022
 */
public interface OrganizationService {
    Organization create(OrganizationModelDTO organizationModelDTO);

    List<Organization> getAll();
}
