package tr.com.metea.hotelium.service.impl;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import tr.com.metea.hotelium.dto.BaseSearchCriteriaDTO;
import tr.com.metea.hotelium.exception.ServiceExecutionException;
import tr.com.metea.hotelium.repository.BaseRepository;
import tr.com.metea.hotelium.service.BaseService;
import tr.com.metea.hotelium.util.MessageUtil;
import tr.com.metea.hotelium.util.SessionContext;
import tr.com.metea.hotelium.util.rsql.CustomRsqlVisitor;

import java.util.List;

/**
 * @author Mete Aydin
 * @since 7.06.2022
 */
@Component
public abstract class BaseServiceImpl<ENTITY, WRITE, SEARCH extends BaseSearchCriteriaDTO<ENTITY>>
        implements BaseService<ENTITY, WRITE, SEARCH> {
    @Autowired()
    protected ModelMapper modelMapper;

    @Autowired()
    protected MessageUtil messageUtil;
    @Autowired()
    protected BaseRepository<ENTITY> repository;

    public ENTITY create(WRITE dto) {
        return repository.save(convertToEntity(dto));
    }

    public ENTITY update(String id, WRITE dto) {
        final var entity = getById(id);
        return repository.save(mapDtoToEntity(entity, dto));
    }

    public ENTITY getById(String id) {
        final var entity = repository.findByIdAndOrgId(id, SessionContext.getSessionData().getOrgId());
        if (entity.isEmpty()) {
            throw new ServiceExecutionException(messageUtil.get("record.notFound.exception"));
        }
        return entity.get();
    }

    public ENTITY delete(String id) {
        final var entity = getById(id);
        repository.delete(entity);
        return entity;
    }

    public Page<ENTITY> search(SEARCH criteria, Pageable pageable) {
        criteria.setOrgId(SessionContext.getSessionData().getOrgId());
        return repository.findAll(criteria.criteriaFieldMapper(), pageable);
    }

    public List<ENTITY> find(String rsqlQueryString) {
        if (!rsqlQueryString.contains("orgId")) {
            rsqlQueryString = "orgId==" + SessionContext.getSessionData().getOrgId().toString() + ";" + rsqlQueryString;
        }
        Node rootNode = new RSQLParser().parse(rsqlQueryString);
        Specification<ENTITY> spec = rootNode.accept(new CustomRsqlVisitor<ENTITY>());
        return repository.findAll(spec);

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
