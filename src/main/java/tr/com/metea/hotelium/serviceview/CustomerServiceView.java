package tr.com.metea.hotelium.serviceview;

import org.springframework.data.domain.Page;
import tr.com.metea.hotelium.domain.Customer;
import tr.com.metea.hotelium.dto.CustomerReadDTO;
import tr.com.metea.hotelium.dto.CustomerSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.CustomerWriteDTO;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface CustomerServiceView extends BaseServiceView<Customer, CustomerWriteDTO, CustomerReadDTO, CustomerSearchCriteriaDTO> {

    CustomerReadDTO assignCompany(String id, String companyId);

    Page<CustomerReadDTO> searchForAutoComplete(CustomerSearchCriteriaDTO filter);

}
