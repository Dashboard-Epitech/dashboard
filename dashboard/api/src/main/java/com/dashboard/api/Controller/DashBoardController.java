package com.dashboard.api.Controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Entity.DashBoard;
import com.dashboard.api.Service.DashBoardService;

@RestController
@RequestMapping("dashboard")
public class DashBoardController {
    @Autowired
    DashBoardService dashboardService;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Object createDashBoard(@RequestBody String body) {
        try {
            DashBoard dashBoard = (DashBoard) this.dashboardService.createDashBoard(body);
            return new ResponseEntity<Integer>(dashBoard.getId(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Object addWidget(@RequestBody String body) {
        try {
            DashBoard dashBoard = (DashBoard) this.dashboardService.addWidget(body);
            return new ResponseEntity<Integer>(dashBoard.getId(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
