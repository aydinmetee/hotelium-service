package tr.com.metea.hotelium.serviceview;

import tr.com.metea.hotelium.domain.Sku;
import tr.com.metea.hotelium.dto.SkuReadDTO;
import tr.com.metea.hotelium.dto.SkuSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.SkuWriteDTO;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
public interface SkuServiceView extends BaseServiceView<Sku, SkuWriteDTO, SkuReadDTO, SkuSearchCriteriaDTO>{
}
