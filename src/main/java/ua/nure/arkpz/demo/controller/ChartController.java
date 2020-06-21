package ua.nure.arkpz.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.arkpz.demo.chart.CustomerChart;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.chart.Chart;
import ua.nure.arkpz.demo.model.User;
import ua.nure.arkpz.demo.service.BuildingService;
import ua.nure.arkpz.demo.service.ChartService;

import java.sql.Date;

@RestController
public class ChartController {
    private final BuildingService buildingService;
    private final ChartService chartService;

    public ChartController(BuildingService buildingService, ChartService chartService) {
        this.buildingService = buildingService;
        this.chartService = chartService;
    }

    @GetMapping("api/statistics/period/chart/all")
    public Object getChartByBuilding(@AuthenticationPrincipal User currentUser,
                                     @RequestParam Long buildingId) {
        Building building = buildingService.findById(buildingId);
        User user = building.getUser();
        if (!currentUser.equals(user)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
     /*   if (!building.equals(buildingService.findByOwner(user).get(0))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
*/
//        Chart chart = chartService.createChartForAllBuildingHistories(building);
        CustomerChart chart = chartService.createNewChartForAllBuilding(building);
        return new ResponseEntity<>(chart, HttpStatus.OK);
    }

    @GetMapping("/statistics/period/chart/date")
    public Object getChartByDate(@AuthenticationPrincipal User currentUser,
                                 @RequestParam Long buildingId,
                                 @RequestParam Date date) {
        Building building = buildingService.findById(buildingId);
        User user = building.getUser();
        if (!currentUser.equals(user)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
       /* if (!building.equals(buildingService.findByOwner(user).get(0))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/

        Chart chart = chartService.createChartForBuildingByDate(building, date);
        return new ResponseEntity<>(chart, HttpStatus.OK);
    }

    @GetMapping("/statistics/period/chart/period")
    public Object getChartByPeriod(@AuthenticationPrincipal User currentUser,
                                   @RequestParam Long buildingId,
                                   @RequestParam Date from, @RequestParam Date to) {
        Building building = buildingService.findById(buildingId);
        User user = building.getUser();
        if (!currentUser.getEmail().equals(user.getEmail())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
       /* if (!building.equals(buildingService.findByOwner(user).get(0))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/

        // Chart chart = chartService.createChartForBuildingByPeriod(building, from, to);
        Chart chart = chartService.createNewCustomerChartPeriod(building, from, to);
        return new ResponseEntity<>(chart, HttpStatus.OK);
    }
}
