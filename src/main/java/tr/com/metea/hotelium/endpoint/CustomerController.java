package tr.com.metea.hotelium.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.metea.hotelium.domain.Customer;
import tr.com.metea.hotelium.dto.CustomerReadDTO;
import tr.com.metea.hotelium.dto.CustomerSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.CustomerWriteDTO;
import tr.com.metea.hotelium.serviceview.CustomerServiceView;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController extends BaseController<Customer, CustomerWriteDTO, CustomerReadDTO, CustomerSearchCriteriaDTO> {
    private final CustomerServiceView customerServiceView;

    @GetMapping("/{customerId}/assign-company/{companyId}")
    public ResponseEntity<CustomerReadDTO> getById(@PathVariable("customerId") String customerId,
                                                   @PathVariable("companyId") String companyId) {
        return ResponseEntity.ok(customerServiceView.assignCompany(customerId, companyId));
    }
}
