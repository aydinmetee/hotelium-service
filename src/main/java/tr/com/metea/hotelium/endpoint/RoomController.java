package tr.com.metea.hotelium.endpoint;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.metea.hotelium.domain.Room;
import tr.com.metea.hotelium.dto.RoomReadDTO;
import tr.com.metea.hotelium.dto.RoomSearchCriteriaDTO;
import tr.com.metea.hotelium.dto.RoomWriteDTO;
import tr.com.metea.hotelium.serviceview.RoomServiceView;

import java.util.List;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@Api(value = "/room")
public class RoomController extends BaseController<Room, RoomReadDTO, RoomWriteDTO, RoomSearchCriteriaDTO> {

    private final RoomServiceView roomServiceView;

    @GetMapping("/{roomId}/mark-as-clean")
    @ApiOperation(value = "Mark as clean", response = RoomReadDTO.class)
    public ResponseEntity<RoomReadDTO> markAsClean(@PathVariable("roomId") String roomId) {
        return ResponseEntity.ok(roomServiceView.markAsClean(roomId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find")
    @ResponseBody
    public ResponseEntity<List<RoomReadDTO>> find(@RequestParam(value = "search") String search) {
        return ResponseEntity.ok(roomServiceView.find(search));
    }
}
