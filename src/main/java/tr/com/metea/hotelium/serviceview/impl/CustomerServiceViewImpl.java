package tr.com.metea.hotelium.serviceview.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.Customer;
import tr.com.metea.hotelium.dto.CustomerReadDTO;
import tr.com.metea.hotelium.dto.CustomerSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.CustomerWriteDTO;
import tr.com.metea.hotelium.service.CustomerService;
import tr.com.metea.hotelium.serviceview.CustomerServiceView;

import java.util.Objects;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceViewImpl extends BaseServiceViewImpl<Customer, CustomerWriteDTO, CustomerReadDTO, CustomerSearchCriteriaDTO>
        implements CustomerServiceView {
    private final CustomerService customerService;


    @Override
    public CustomerReadDTO assignCompany(String id, String companyId) {
        return convertToDTO(customerService.assignCompany(id, companyId));
    }

    @Override
    public Page<CustomerReadDTO> searchForAutoComplete(CustomerSearchCriteriaDTO filter) {
        return customerService.searchForAutoComplete(filter).map(this::convertToDTO);
    }

    @Override
    public CustomerReadDTO convertToDTO(Customer customer) {
        final var customerReadDTO = modelMapper.map(customer, CustomerReadDTO.class);
        if (Objects.nonNull(customer.getCompany())) {
            customerReadDTO.setCompanyName(customer.getCompany().getNameTitle());
            customerReadDTO.setCompanyId(customer.getCompany().getId());
        }
        return customerReadDTO;
    }
}
