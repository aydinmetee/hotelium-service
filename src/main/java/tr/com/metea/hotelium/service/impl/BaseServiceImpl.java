package tr.com.metea.hotelium.service.impl;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import tr.com.metea.hotelium.dto.BaseSearchCriteriaDTO;
import tr.com.metea.hotelium.exception.ServiceExecutionException;
import tr.com.metea.hotelium.service.BaseService;
import tr.com.metea.hotelium.util.rsql.CustomRsqlVisitor;

import java.util.List;

/**
 * @author Mete Aydin
 * @since 7.06.2022
 */
@Component
public abstract class BaseServiceImpl<ENTITY, WRITE, SEARCH extends BaseSearchCriteriaDTO<ENTITY>>
        implements BaseService<ENTITY, WRITE, SEARCH> {
    //TODO:Protected olarak messageutil buraya alınacak. hatta modelmapper da
    @Autowired()
    protected JpaRepository<ENTITY, String> repository;

    @Autowired()
    protected JpaSpecificationExecutor<ENTITY> specificationExecutor;

    public ENTITY create(WRITE dto) {
        return repository.save(convertToEntity(dto));
    }

    public ENTITY update(String id, WRITE dto) {
        final var entity = getById(id);
        return repository.save(mapDtoToEntity(entity, dto));
    }

    public ENTITY getById(String id) {
        final var entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new ServiceExecutionException("Kayıt Bulunamadı!");
        }
        return entity.get();
    }

    public ENTITY delete(String id) {
        final var entity = getById(id);
        repository.delete(entity);
        return entity;
    }

    public Page<ENTITY> search(SEARCH criteria, Pageable pageable) {
        return specificationExecutor.findAll(criteria.criteriaFieldMapper(), pageable);
    }

    public List<ENTITY> find(String rsqlQueryString) {
        Node rootNode = new RSQLParser().parse(rsqlQueryString);
        Specification<ENTITY> spec = rootNode.accept(new CustomRsqlVisitor<ENTITY>());
        return specificationExecutor.findAll(spec);

    }

    // This Function Must Be Writen.
    public ENTITY convertToEntity(WRITE dto) {
        return null;
    }

    // This Function Must Be Writen.
    public ENTITY mapDtoToEntity(ENTITY entity, WRITE dto) {
        return null;
    }


}
