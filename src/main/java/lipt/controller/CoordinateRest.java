package lipt.controller;

import lipt.model.Coordinate;
import lipt.utils.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
public class CoordinateRest {

    private RequestService requestService;

    @Autowired
    public CoordinateRest(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<Coordinate> post(@RequestBody String name) {
        Coordinate coordinate = requestService.makeRequest(name);

        return ResponseEntity.ok(coordinate);
    }
}
