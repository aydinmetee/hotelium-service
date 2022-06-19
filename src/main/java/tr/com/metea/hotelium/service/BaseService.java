package tr.com.metea.hotelium.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Mete Aydin
 * @since 7.06.2022
 */
public interface BaseService<E, W, S> {
    E create(W dto);

    E update(String id, W dto);

    E getById(String id);

    E delete(String id);

    Page<E> search(S criteria, Pageable pageable);

    E convertToEntity(W dto);

    E mapDtoToEntity(E entity, W dto);
}
