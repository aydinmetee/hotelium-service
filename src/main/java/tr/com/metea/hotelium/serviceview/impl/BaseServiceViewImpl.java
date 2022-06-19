package tr.com.metea.hotelium.serviceview.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import tr.com.metea.hotelium.service.BaseService;
import tr.com.metea.hotelium.serviceview.BaseServiceView;

/**
 * @author Mete Aydin
 * @since 7.06.2022
 */
@Component
public abstract class BaseServiceViewImpl<READ, WRITE, ENTITY, SEARCH>
        implements BaseServiceView<ENTITY, READ, WRITE, SEARCH> {

    @Autowired
    protected BaseService<ENTITY, WRITE, SEARCH> service;

    public READ create(WRITE dto) {
        return convertToDTO(service.create(dto));
    }

    public READ update(String id, WRITE dto) {
        return convertToDTO(service.update(id, dto));
    }

    public READ getById(String id) {
        return convertToDTO(service.getById(id));
    }

    public READ delete(String id) {
        return convertToDTO(service.delete(id));
    }

    public Page<READ> search(SEARCH criteria, Pageable pageable) {
        return service.search(criteria, pageable).map(this::convertToDTO);
    }

    // This Function Must Be Writen.
    public READ convertToDTO(ENTITY entity) {
        return null;
    }
}
