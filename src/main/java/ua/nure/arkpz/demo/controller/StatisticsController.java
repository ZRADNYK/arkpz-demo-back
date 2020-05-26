package ua.nure.arkpz.demo.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.User;
import ua.nure.arkpz.demo.service.BuildingService;
import ua.nure.arkpz.demo.service.RecommendationsService;
import ua.nure.arkpz.demo.service.StatisticsService;
import ua.nure.arkpz.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
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

    @PatchMapping(value = "/statistics/period/chart",
            produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody ResponseEntity<byte[]> getChartAsImageByPeriod(@AuthenticationPrincipal User currentUser,
                                                   @RequestBody User user, @RequestParam Long buildingId,
                                                   @RequestParam Date from, @RequestParam Date to,
                                                   HttpServletRequest request) throws IOException {
        Building building = buildingService.findById(buildingId);
        if (!currentUser.getEmail().equals(user.getEmail())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

/*
        if (!building.equals(buildingService.findByOwner(user).get(0))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
*/
        InputStream in = request.getServletContext().getResourceAsStream("/WEB-INF/images/chart.png");
        return new ResponseEntity<>(IOUtils.toByteArray(in), HttpStatus.OK);
    }
}
