package ua.nure.arkpz.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.User;
import ua.nure.arkpz.demo.service.BuildingService;
import ua.nure.arkpz.demo.service.RecommendationsService;
import ua.nure.arkpz.demo.service.StatisticsService;
import ua.nure.arkpz.demo.service.UserService;

import java.sql.Date;

@RestController
public class StatisticsController {
    private final StatisticsService statisticsService;
    private final BuildingService buildingService;
    private final UserService userService;
    private final RecommendationsService recommendationsService;

    public StatisticsController(StatisticsService statisticsService, BuildingService buildingService,
                                UserService userService, RecommendationsService recommendationsService) {
        this.statisticsService = statisticsService;
        this.buildingService = buildingService;
        this.userService = userService;
        this.recommendationsService = recommendationsService;
    }

    @GetMapping("/statistics/period")
    public Object getStatisticsByPeriod(@AuthenticationPrincipal User currentUser,
                                        @RequestBody User user, @RequestParam Long buildingId,
                                        @RequestBody Date from, @RequestBody Date to) {
        Building building = buildingService.findById(buildingId);
        if (!currentUser.equals(user)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (!building.equals(buildingService.findByOwner(user))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recommendationsService.prepareRecommendationsForBuilding(building);
        return new ResponseEntity(statisticsService.getStatisticsByPeriod(building, from, to), HttpStatus.OK);
    }

    @GetMapping("/statistics/date")
    public Object getStatisticsByDate(@RequestParam Long userId, @RequestParam Long buildingId,
                                      @RequestParam Date date) {
        Building building = buildingService.findById(buildingId);
        User user = userService.findByUserId(userId);
        if (!building.equals(buildingService.findByOwner(user))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recommendationsService.prepareRecommendationsForBuilding(building);
        return new ResponseEntity(statisticsService.getStatisticsByDate(building, date), HttpStatus.OK);
    }
}
