package tr.com.metea.hotelium.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import tr.com.metea.hotelium.serviceview.BaseServiceView;

import java.util.List;

/**
 * @author Mete Aydin
 * @since 7.06.2022
 */
@Component
public abstract class BaseController<ENTITY, WRITE, READ, SEARCH> {

    @Autowired
    protected BaseServiceView<ENTITY, WRITE, READ, SEARCH> service;

    @PostMapping
    public ResponseEntity<READ> create(@RequestBody WRITE dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<READ> update(@PathVariable("id") String id, @RequestBody WRITE dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<READ> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<READ> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<READ>> search(@RequestBody() SEARCH filter, Pageable pageable) {
        return ResponseEntity.ok(service.search(filter, pageable));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find")
    @ResponseBody
    public ResponseEntity<List<READ>> find(@RequestParam(value = "query") String query) {
        return ResponseEntity.ok(service.find(query));
    }

}
