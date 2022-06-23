package tr.com.metea.hotelium.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.metea.hotelium.dto.CompanySearchCriteriaDTO;
import tr.com.metea.hotelium.dto.CustomerSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.RoomSearchCriteriaDTO;
import tr.com.metea.hotelium.serviceview.CompanyServiceView;
import tr.com.metea.hotelium.serviceview.CustomerServiceView;
import tr.com.metea.hotelium.serviceview.RoomServiceView;
import tr.com.metea.hotelium.util.KeyValue;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/combo")
@RequiredArgsConstructor
@Api(value = "/combo")
@Slf4j
public class ComboController {
    private final RoomServiceView roomServiceView;
    private final CompanyServiceView companyServiceView;
    private final CustomerServiceView customerServiceView;

    @PostMapping("/rooms")
    @ApiOperation(value = "Rooms Combo", response = Page.class)
    public ResponseEntity<List<KeyValue>> rooms(@RequestBody() RoomSearchCriteriaDTO filter) {
        final var pagingResult = roomServiceView.search(filter, PageRequest.of(0, 1000, Sort.by(Sort.Order.asc("code"))));
        List<KeyValue> keyValues = new ArrayList<>();
        if (pagingResult.hasContent()) {
            pagingResult.getContent().forEach(roomReadDTO -> keyValues.add(new KeyValue(roomReadDTO.getCode(), roomReadDTO.getId())));
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }

    @PostMapping("/companys")
    @ApiOperation(value = "Company Combo", response = Page.class)
    public ResponseEntity<List<KeyValue>> companys(@RequestBody() CompanySearchCriteriaDTO filter) {
        final var pagingResult = companyServiceView.search(filter, PageRequest.of(0, 1000));
        List<KeyValue> keyValues = new ArrayList<>();
        if (pagingResult.hasContent()) {
            pagingResult.getContent().forEach(companyReadDTO -> keyValues.add(new KeyValue(companyReadDTO.getNameTitle(), companyReadDTO.getId())));
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }

    @PostMapping("/customers")
    @ApiOperation(value = "Company Combo", response = Page.class)
    public ResponseEntity<List<KeyValue>> customers(@RequestBody() CustomerSearchCriteriaDTO filter) {
        final var pagingResult = customerServiceView.search(filter, PageRequest.of(0, 1000));
        List<KeyValue> keyValues = new ArrayList<>();
        if (pagingResult.hasContent()) {
            pagingResult.getContent().forEach(customerReadDTO -> keyValues.add(new KeyValue(customerReadDTO.getName() + " " + customerReadDTO.getLastname(), customerReadDTO.getId())));
        }
        return new ResponseEntity<>(keyValues, HttpStatus.OK);
    }

}
