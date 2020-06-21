package ua.nure.arkpz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.Recommendation;
import ua.nure.arkpz.demo.model.User;
import ua.nure.arkpz.demo.service.BuildingService;
import ua.nure.arkpz.demo.service.RecommendationsService;
import ua.nure.arkpz.demo.service.UserService;

@RestController
public class RecommendationsController {
    private final RecommendationsService recommendationsService;
    private final BuildingService buildingService;
    private final UserService userService;

    @Autowired
    public RecommendationsController(RecommendationsService recommendationsService, BuildingService buildingService, UserService userService) {
        this.recommendationsService = recommendationsService;
        this.buildingService = buildingService;
        this.userService = userService;
    }

    @GetMapping("/api/recommendations")
    public ResponseEntity<Recommendation> getRecommendations(@AuthenticationPrincipal User currentUser,
                                                             @RequestParam Long buildingId) {
        User user = userService.findByUserId(currentUser.getUserId());
        Building building = buildingService.findById(buildingId);
        if (!user.getUserId().equals(building.getUser().getUserId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Recommendation recommendation = recommendationsService.prepareRecommendationsForBuilding(building);
        return ResponseEntity.ok(recommendation);
    }
}
