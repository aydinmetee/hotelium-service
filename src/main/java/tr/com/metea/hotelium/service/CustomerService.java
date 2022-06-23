package tr.com.metea.hotelium.service;

import org.springframework.data.domain.Page;
import tr.com.metea.hotelium.domain.Customer;
import tr.com.metea.hotelium.dto.CustomerSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.CustomerWriteDTO;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface CustomerService extends BaseService<Customer, CustomerWriteDTO, CustomerSearchCriteriaDTO> {

    Customer assignCompany(String id, String companyId);

    Page<Customer> searchForAutoComplete(CustomerSearchCriteriaDTO filter);
}
