package tr.com.metea.hotelium.endpoint;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.metea.hotelium.domain.Sku;
import tr.com.metea.hotelium.dto.SkuReadDTO;
import tr.com.metea.hotelium.dto.SkuSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.SkuWriteDTO;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
@RestController
@RequestMapping("/sku")
@RequiredArgsConstructor
@Api(value = "/sku")
public class SkuController extends BaseController<Sku, SkuWriteDTO, SkuReadDTO, SkuSearchCriteriaDTO> {
}
