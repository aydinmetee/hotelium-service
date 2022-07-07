package tr.com.metea.hotelium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.Sku;
import tr.com.metea.hotelium.dto.SkuSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.SkuWriteDTO;
import tr.com.metea.hotelium.service.SkuService;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
@Service
@RequiredArgsConstructor
public class SkuServiceImpl extends BaseServiceImpl<Sku, SkuWriteDTO, SkuSearchCriteriaDTO>
        implements SkuService {

    @Override
    public Sku convertToEntity(SkuWriteDTO dto) {
        return modelMapper.map(dto, Sku.class);
    }

    @Override
    public Sku mapDtoToEntity(Sku sku, SkuWriteDTO dto) {
        sku.setCode(dto.getCode());
        sku.setName(dto.getName());
        sku.setStock(dto.getStock());
        sku.setUnitPrice(dto.getUnitPrice());
        return sku;
    }
}
