package tr.com.metea.hotelium.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.metea.hotelium.annotation.NoSession;
import tr.com.metea.hotelium.dto.CompanySearchCriteriaDTO;
import tr.com.metea.hotelium.dto.CustomerSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.DraweeComboDTO;
import tr.com.metea.hotelium.dto.ReservationDetailSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.RoomSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.SkuSearchCriteriaDTO;
import tr.com.metea.hotelium.service.LocationService;
import tr.com.metea.hotelium.serviceview.CompanyServiceView;
import tr.com.metea.hotelium.serviceview.CustomerServiceView;
import tr.com.metea.hotelium.serviceview.OrganizationServiceView;
import tr.com.metea.hotelium.serviceview.ReservationDetailServiceView;
import tr.com.metea.hotelium.serviceview.RoomServiceView;
import tr.com.metea.hotelium.serviceview.SkuServiceView;
import tr.com.metea.hotelium.util.KeyValue;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/combo")
@RequiredArgsConstructor
@Slf4j
public class ComboController {
    private final RoomServiceView roomServiceView;
    private final CompanyServiceView companyServiceView;
    private final CustomerServiceView customerServiceView;

    private final ReservationDetailServiceView reservationDetailServiceView;

    private final LocationService locationService;

    private final SkuServiceView skuServiceView;

    private final OrganizationServiceView organizationServiceView;

    @PostMapping("/rooms")
    public ResponseEntity<List<KeyValue>> rooms(@RequestBody() RoomSearchCriteriaDTO filter) {
        final var pagingResult = roomServiceView.search(filter, PageRequest.of(0, 1000, Sort.by(Sort.Order.asc("code"))));
        List<KeyValue> keyValues = new ArrayList<>();
        if (pagingResult.hasContent()) {
            pagingResult.getContent().forEach(roomReadDTO -> keyValues.add(new KeyValue(roomReadDTO.getCode(), roomReadDTO.getId())));
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }

    @PostMapping("/companys")
    public ResponseEntity<List<KeyValue>> companys(@RequestBody() CompanySearchCriteriaDTO filter) {
        final var pagingResult = companyServiceView.search(filter, PageRequest.of(0, 1000));
        List<KeyValue> keyValues = new ArrayList<>();
        if (pagingResult.hasContent()) {
            pagingResult.getContent().forEach(companyReadDTO -> keyValues.add(new KeyValue(companyReadDTO.getNameTitle(), companyReadDTO.getId())));
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<List<KeyValue>> customers(@RequestBody() CustomerSearchCriteriaDTO filter) {
        final var pagingResult = customerServiceView.search(filter, PageRequest.of(0, 1000));
        List<KeyValue> keyValues = new ArrayList<>();
        if (pagingResult.hasContent()) {
            pagingResult.getContent().forEach(customerReadDTO -> keyValues.add(new KeyValue(customerReadDTO.getName() + " " + customerReadDTO.getLastname(), customerReadDTO.getId())));
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }

    @GetMapping("/drawee/{reservationMasterId}")
    public ResponseEntity<List<KeyValue>> draweeForReservation(@PathVariable("reservationMasterId") String reservationMasterId) {
        final var filter = new ReservationDetailSearchCriteriaDTO();
        filter.setReservationMasterId(reservationMasterId);
        final var pagingResult = reservationDetailServiceView
                .search(filter, PageRequest.of(0, 1000));
        List<KeyValue> keyValues = new ArrayList<>();
        if (pagingResult.hasContent()) {
            pagingResult.getContent().forEach(reservationDetailReadDTO -> {
                keyValues.add(
                        new KeyValue(reservationDetailReadDTO.getCustomerFullName() + " - " + reservationDetailReadDTO.getCustomerLegalId(),
                                reservationDetailReadDTO.getCustomerId(), "PERSON"));
                if (!StringUtils.isEmpty(reservationDetailReadDTO.getCompanyId())) {
                    var company = new KeyValue(reservationDetailReadDTO.getCompanyName() + " - " + reservationDetailReadDTO.getCompanyLegalNo(),
                            reservationDetailReadDTO.getCompanyId(), "LEGAL");
                    if (!keyValues.contains(company)) {
                        keyValues.add(company);
                    }
                }
            });
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }

    @PostMapping("/drawees")
    public ResponseEntity<List<KeyValue>> drawees(@RequestBody() DraweeComboDTO filter) {
        final var customerFilter = new CustomerSearchCriteriaDTO();
        customerFilter.setName(filter.getNameTitle());
        customerFilter.setLastname(filter.getNameTitle());
        List<KeyValue> keyValues = new ArrayList<>();

        final var customerPagingResult = customerServiceView.searchForAutoComplete(customerFilter);
        if (customerPagingResult.hasContent()) {
            log.info("Customer Length : {}", customerPagingResult.getTotalElements());
            customerPagingResult.getContent().forEach(customerReadDTO -> keyValues.add(
                    new KeyValue(customerReadDTO.getName() + " " + customerReadDTO.getLastname(), customerReadDTO.getId())));
        }
        final var companyFilter = new CompanySearchCriteriaDTO();
        companyFilter.setNameTitle(filter.getNameTitle());
        final var companyPagingResult = companyServiceView.search(companyFilter, PageRequest.of(0, 1000));
        if (companyPagingResult.hasContent()) {
            companyPagingResult.getContent().forEach(customerReadDTO -> keyValues.add(
                    new KeyValue(customerReadDTO.getNameTitle(), customerReadDTO.getId())));
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }

    @GetMapping("/countries")
    public ResponseEntity<List<KeyValue>> countries() {

        List<KeyValue> keyValues = new ArrayList<>();

        final var countryResult = locationService.getCountries();
        if (!countryResult.isEmpty()) {
            log.info("Customer Length : {}", countryResult.size());
            countryResult.forEach(country -> keyValues.add(
                    new KeyValue(country.getName(), country.getId())));
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }

    @GetMapping("/cities/{countryId}")
    public ResponseEntity<List<KeyValue>> cities(@PathVariable("countryId") String countryId) {

        List<KeyValue> keyValues = new ArrayList<>();

        final var cityResult = locationService.getCitiesByCountry(countryId);
        if (!cityResult.isEmpty()) {
            log.info("Customer Length : {}", cityResult.size());
            cityResult.forEach(city -> keyValues.add(
                    new KeyValue(city.getName(), city.getId())));
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }

    @GetMapping("/towns/{cityId}")
    public ResponseEntity<List<KeyValue>> towns(@PathVariable("cityId") String cityId) {

        List<KeyValue> keyValues = new ArrayList<>();

        final var townResult = locationService.getTownsByCity(cityId);
        if (!townResult.isEmpty()) {
            log.info("Customer Length : {}", townResult.size());
            townResult.forEach(town -> keyValues.add(
                    new KeyValue(town.getName(), town.getId())));
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }

    @PostMapping("/skus")
    public ResponseEntity<List<KeyValue>> skus(@RequestBody() SkuSearchCriteriaDTO filter) {
        final var pagingResult = skuServiceView.search(filter, PageRequest.of(0, 1000));
        List<KeyValue> keyValues = new ArrayList<>();
        if (pagingResult.hasContent()) {
            pagingResult.getContent().forEach(skuReadDTO -> keyValues.add(new KeyValue(skuReadDTO.getCode() + " - " + skuReadDTO.getName(), skuReadDTO.getId())));
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }

    @NoSession
    @GetMapping("/orgs")
    public ResponseEntity<List<KeyValue>> skus() {
        final var pagingResult = organizationServiceView.getAll();
        List<KeyValue> keyValues = new ArrayList<>();
        if (!pagingResult.isEmpty()) {
            pagingResult.forEach(orgReadDTO -> keyValues.add(new KeyValue(orgReadDTO.getName() + " - " + orgReadDTO.getCode(), orgReadDTO.getId())));
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }
}
