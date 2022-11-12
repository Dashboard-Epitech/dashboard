package com.dashboard.api.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Entity.DashBoard;
import com.dashboard.api.Error.DashboardResponseError;
import com.dashboard.api.Error.WidgetResponseError;
import com.dashboard.api.Request.DashBoardRequest;
import com.dashboard.api.Request.DashboardAddWidgetRequest;
import com.dashboard.api.Service.DashBoardService;

@RestController
@RequestMapping("dashboard")
public class DashBoardController {
    @Autowired
    DashBoardService dashboardService;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createDashBoard(@RequestBody @Valid DashBoardRequest request) {
        try {
            DashBoard dashBoard = (DashBoard) this.dashboardService.createDashBoard(request);
            return ResponseEntity.ok().body(dashBoard);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addWidget(@RequestBody @Valid DashboardAddWidgetRequest request) {
        try {
            DashBoard dashBoard = (DashBoard) this.dashboardService.addWidget(request);
            return ResponseEntity.ok().body(dashBoard);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> update(@PathVariable(value = "id") String id,
            @RequestBody @Valid DashBoardRequest request) {
        try {
            return ResponseEntity.badRequest().body(this.dashboardService.update(Long.parseLong(id), request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(path = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id) {
        try {
            return ResponseEntity.ok().body(this.dashboardService.remove(Long.parseLong(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, errorMessage);
        });

        DashboardResponseError dashboardResponseError = new DashboardResponseError(validationErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dashboardResponseError.build());
    }
}
