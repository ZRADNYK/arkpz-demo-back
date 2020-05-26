package ua.nure.arkpz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.Recommendation;
import ua.nure.arkpz.demo.model.User;
import ua.nure.arkpz.demo.service.BuildingService;
import ua.nure.arkpz.demo.service.RecommendationsService;

@RestController
public class RecommendationsController {
    private final RecommendationsService recommendationsService;
    private final BuildingService buildingService;

    @Autowired
    public RecommendationsController(RecommendationsService recommendationsService, BuildingService buildingService) {
        this.recommendationsService = recommendationsService;
        this.buildingService = buildingService;
    }

    @GetMapping("/recommendations")
    public ResponseEntity<Recommendation> getRecommendations(@AuthenticationPrincipal User currentUser,
                                                             @RequestBody User user,
                                                             @RequestBody Building building) {
        if(!currentUser.equals(user)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if(!building.equals(buildingService.findByOwner(user))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Тікай з міста, закрывай кафе, а то от короны сдохнешь", HttpStatus.OK);
       // return new ResponseEntity(recommendationsService.prepareRecommendationsForBuilding(building), HttpStatus.OK);
    }
}
