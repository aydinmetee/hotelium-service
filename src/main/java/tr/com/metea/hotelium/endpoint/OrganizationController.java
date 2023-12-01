package tr.com.metea.hotelium.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.metea.hotelium.annotation.NoSession;
import tr.com.metea.hotelium.dto.OrganizationModelDTO;
import tr.com.metea.hotelium.serviceview.OrganizationServiceView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mete Aydin
 * @since 18.03.2022
 */
@RestController
@RequestMapping("/org-def")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationServiceView organizationServiceView;

    @NoSession
    @PostMapping()
    public ResponseEntity<OrganizationModelDTO> create(@RequestBody OrganizationModelDTO organizationModelDTO) {
        return ResponseEntity.ok(organizationServiceView.create(organizationModelDTO));
    }
}
