package tr.com.metea.hotelium.endpoint;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.metea.hotelium.domain.Company;
import tr.com.metea.hotelium.dto.CompanyReadDTO;
import tr.com.metea.hotelium.dto.CompanySearchCriteriaDTO;
import tr.com.metea.hotelium.dto.CompanyWriteDTO;
import tr.com.metea.hotelium.serviceview.CompanyServiceView;

/**
 * @author Mete Aydin
 * @since 23.10.2021
 */
@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@Api(value = "/company")
public class CompanyController extends BaseController<Company, CompanyWriteDTO, CompanyReadDTO, CompanySearchCriteriaDTO> {
    private final CompanyServiceView companyServiceView;

}
