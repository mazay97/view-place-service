package lipt.controller;

import lipt.model.Coordinate;
import lipt.model.Place;
import lipt.utils.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CoordinateController {

    private RequestService requestService;

    @Autowired
    public CoordinateController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/coordinate")
    public String coordinateForm(Model model) {
        model.addAttribute("place", new Place());
        return "coordinate";
    }

    @PostMapping("/coordinate")
    public String coordinateSubmit(@ModelAttribute Place place, Model model) {
        Coordinate coordinate = requestService.makeRequest(place.getContent());

        model.addAttribute("latitude", coordinate.geo_lat);
        model.addAttribute("longitude", coordinate.geo_lon);

        return "result";
    }

}
