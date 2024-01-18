package tr.com.metea.hotelium.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tr.com.metea.hotelium.domain.Customer;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
public interface CustomerRepository extends BaseRepository<Customer> {

    Page<Customer> getCustomersByNameContainsOrLastnameContainsAndOrgId(String name, String lastName, String orgId, Pageable pageable);
}
