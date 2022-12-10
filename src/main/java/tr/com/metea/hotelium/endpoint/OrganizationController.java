package tr.com.metea.hotelium.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.metea.hotelium.annotation.NoSession;
import tr.com.metea.hotelium.dto.OrganizationModelDTO;
import tr.com.metea.hotelium.serviceview.OrganizationServiceView;
import tr.com.metea.hotelium.util.KeyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mete Aydin
 * @since 18.03.2022
 */
@RestController
@RequestMapping("/org-def")
@RequiredArgsConstructor
@Api(value = "/org-def")
public class OrganizationController {
    private final OrganizationServiceView organizationServiceView;

    @NoSession
    @PostMapping()
    @ApiOperation(value = "Organization Def", response = OrganizationModelDTO.class)
    public ResponseEntity<OrganizationModelDTO> create(@RequestBody OrganizationModelDTO organizationModelDTO) {
        return ResponseEntity.ok(organizationServiceView.create(organizationModelDTO));
    }
}
