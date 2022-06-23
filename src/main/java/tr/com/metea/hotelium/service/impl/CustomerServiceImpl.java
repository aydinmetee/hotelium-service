package tr.com.metea.hotelium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.Customer;
import tr.com.metea.hotelium.dto.CustomerSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.CustomerWriteDTO;
import tr.com.metea.hotelium.repository.CustomerRepository;
import tr.com.metea.hotelium.service.CompanyService;
import tr.com.metea.hotelium.service.CustomerService;
import tr.com.metea.hotelium.util.SessionContext;

import java.util.Optional;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends BaseServiceImpl<Customer, CustomerWriteDTO, CustomerSearchCriteriaDTO>
        implements CustomerService {
    private final CustomerRepository customerRepository;
    @Lazy
    private final CompanyService companyService;

    @Override
    public Customer assignCompany(String id, String companyId) {
        final var company = companyService.getById(companyId);
        final var customerDb = getById(id);
        customerDb.setCompany(company);
        return customerRepository.save(customerDb);
    }

    @Override
    public Page<Customer> searchForAutoComplete(CustomerSearchCriteriaDTO filter) {
        return customerRepository.getCustomersByNameContainsOrLastnameContainsAndOrgId(
                Optional.ofNullable(filter.getName()).orElse(""),
                Optional.ofNullable(filter.getLastname()).orElse(""),
                SessionContext.getSessionData().getOrgId(), PageRequest.of(0, 1000));
    }

    @Override
    public Customer convertToEntity(CustomerWriteDTO dto) {
        return modelMapper.map(dto, Customer.class);
    }

    @Override
    public Customer mapDtoToEntity(Customer customer, CustomerWriteDTO dto) {
        customer.setName(dto.getName());
        customer.setLastname(dto.getLastname());
        customer.setPhone(dto.getPhone());
        customer.setLegalId(dto.getLegalId());
        return customer;
    }
}
