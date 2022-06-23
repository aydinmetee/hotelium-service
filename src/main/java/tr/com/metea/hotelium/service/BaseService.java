package tr.com.metea.hotelium.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Mete Aydin
 * @since 7.06.2022
 */
public interface BaseService<ENTITY, WRITE, SEARCH> {
    ENTITY create(WRITE dto);

    ENTITY update(String id, WRITE dto);

    ENTITY getById(String id);

    ENTITY delete(String id);

    Page<ENTITY> search(SEARCH criteria, Pageable pageable);

    List<ENTITY> find(String rsqlQueryString);

    ENTITY convertToEntity(WRITE dto);

    ENTITY mapDtoToEntity(ENTITY entity, WRITE dto);

}
