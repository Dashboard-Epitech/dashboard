package com.dashboard.api.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Entity.DashBoard;
import com.dashboard.api.Entity.Widget;
import com.dashboard.api.Error.DashboardResponseError;
import com.dashboard.api.Model.Request.dashboard.GetDashboardWidgetsRequest;
import com.dashboard.api.Request.DashBoardRequest;
import com.dashboard.api.Request.DashboardAddWidgetRequest;
import com.dashboard.api.Security.oauth2.user.DashboardUserPrincipal;
import com.dashboard.api.Service.DashBoardService;

@RestController
@RequestMapping("dashboard")
@CrossOrigin(originPatterns = "http://localhost:*")
public class DashBoardController {
    @Autowired
    DashBoardService dashboardService;

    @PostMapping(path = "/create")
    public ResponseEntity<?> createDashBoard(@AuthenticationPrincipal DashboardUserPrincipal user, @RequestBody @Valid DashBoardRequest request) {
        try {
            DashBoard dashBoard = this.dashboardService.createDashBoard(user.getId(), request.getName());
            return ResponseEntity.ok().body(dashBoard);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<?> getDashboards(@AuthenticationPrincipal DashboardUserPrincipal user) {
        try {
            List<DashBoard> dashboards = dashboardService.getUserDashboards(user.getId());

            return ResponseEntity.ok().body(dashboards);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @PostMapping(path = "/get/widgets")
    public ResponseEntity<?> getDashboardWidgets(@RequestBody GetDashboardWidgetsRequest request) {
        try {
            List<Widget> widgets = dashboardService.getDashboardWidgets(request.getDashboardId());
            return ResponseEntity.ok().body(widgets);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
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
