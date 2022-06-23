package tr.com.metea.hotelium.serviceview;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Mete Aydin
 * @since 7.06.2022
 */
public interface BaseServiceView<ENTITY, WRITE, READ,  SEARCH> {
    READ create(WRITE dto);

    READ update(String id, WRITE dto);

    READ getById(String id);

    READ delete(String id);

    Page<READ> search(SEARCH criteria, Pageable pageable);

    List<READ> find(String query);

    READ convertToDTO(ENTITY entity);
}
