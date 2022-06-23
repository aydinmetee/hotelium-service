package tr.com.metea.hotelium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tr.com.metea.hotelium.domain.Currency;

/**
 * @author Mete Aydin
 * @since 23.06.2022
 */
public interface CurrencyRepository extends JpaRepository<Currency, String>, JpaSpecificationExecutor<Currency> {
}
