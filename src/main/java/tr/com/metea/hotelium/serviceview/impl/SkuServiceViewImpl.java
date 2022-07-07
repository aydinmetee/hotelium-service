package tr.com.metea.hotelium.serviceview.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.Sku;
import tr.com.metea.hotelium.dto.SkuReadDTO;
import tr.com.metea.hotelium.dto.SkuSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.SkuWriteDTO;
import tr.com.metea.hotelium.serviceview.SkuServiceView;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
@Service
@RequiredArgsConstructor
public class SkuServiceViewImpl extends BaseServiceViewImpl<Sku, SkuWriteDTO, SkuReadDTO, SkuSearchCriteriaDTO>
        implements SkuServiceView {
    @Override
    public SkuReadDTO convertToDTO(Sku sku) {
        return modelMapper.map(sku, SkuReadDTO.class);
    }
}
