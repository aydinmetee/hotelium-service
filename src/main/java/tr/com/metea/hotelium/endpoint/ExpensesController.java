package tr.com.metea.hotelium.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.metea.hotelium.domain.ExpensesMaster;
import tr.com.metea.hotelium.dto.*;
import tr.com.metea.hotelium.serviceview.ExpensesDetailServiceView;

/**
 * @author Mete Aydin
 * <p>
 * 7.07.2022
 */
@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpensesController extends BaseController<ExpensesMaster, ExpensesMasterWriteDTO, ExpensesMasterReadDTO, ExpensesMasterSearchCriteriaDTO> {

    private final ExpensesDetailServiceView expensesDetailServiceView;

    @PostMapping("/{masterId}/details")
    public ResponseEntity<ExpensesDetailReadDTO> createDetail(@RequestBody ExpensesDetailWriteDTO expensesDetailWriteDTO,
                                                              @PathVariable("masterId") String masterId) {
        expensesDetailWriteDTO.setExpensesMasterId(masterId);
        return ResponseEntity.ok(expensesDetailServiceView.create(expensesDetailWriteDTO));
    }

    @GetMapping("/{masterId}/details/{detailId}")
    public ResponseEntity<ExpensesDetailReadDTO> getByIdDetail(@PathVariable("detailId") String detailId) {
        return ResponseEntity.ok(expensesDetailServiceView.getById(detailId));
    }

    @DeleteMapping("/{masterId}/details/{detailId}")
    public ResponseEntity<ExpensesDetailReadDTO> deleteDetail(@PathVariable("detailId") String detailId) {
        return ResponseEntity.ok(expensesDetailServiceView.delete(detailId));
    }

    @PostMapping("/{masterId}/details/search")
    public ResponseEntity<Page<ExpensesDetailReadDTO>> searchDetails(@RequestBody() ExpensesDetailSearchCriteriaDTO filter,
                                                                     Pageable pageable) {
        return ResponseEntity.ok(expensesDetailServiceView.search(filter, pageable));
    }

}
