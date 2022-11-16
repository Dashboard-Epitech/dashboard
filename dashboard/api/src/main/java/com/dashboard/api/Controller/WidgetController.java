package com.dashboard.api.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.dashboard.api.Error.WidgetResponseError;
import com.dashboard.api.Model.Request.widget.weather.CreateSpotifyWidget;
import com.dashboard.api.Model.Request.widget.weather.CreateWeatherWidgetRequest;
import com.dashboard.api.Request.WidgetRequest;
import com.dashboard.api.Service.WidgetService;

@RestController
@RequestMapping(path = "/widget")
@CrossOrigin(originPatterns = "http://localhost:*")
public class WidgetController {

    @Autowired
    private WidgetService widgetService;

    @PostMapping("/create/weather")
    public ResponseEntity<?> createWeatherWidget(@RequestBody CreateWeatherWidgetRequest request) {
        try {
            return ResponseEntity.ok().body(
                widgetService.createWeatherWidget(request.getDashboardId(), request.getCity(), request.getUnit(), request.getSize(), request.getRefreshRate())
            );
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @PostMapping("/create/spotify")
    public ResponseEntity<?> createSpotifyWidget(@RequestBody CreateSpotifyWidget request) {
        try {
            return ResponseEntity.ok().body(
                widgetService.createSpotifyWidget(request.getDashboardId(), request.getType(), request.getSize(), request.getTrackId(), request.getPlaylistId())
            );
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    // public ResponseEntity<?> createWidget(WidgetRequest request, WidgetService service) {
    //     try {
    //         return ResponseEntity.status(HttpStatus.CREATED).body(service.createWidget(request));
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

    // public ResponseEntity<?> updateWidget(long id, WidgetRequest request, WidgetService service) {
    //     try {
    //         return ResponseEntity.ok().body(service.updateWidget(id, request));
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

    // public ResponseEntity<?> updateData(long id, WidgetService service) {
    //     try {
    //         return ResponseEntity.ok().body(service.updateData(id));
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

    // @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    // public ResponseEntity<?> remove(@PathVariable(value = "id") String id) {
    //     try {
    //         return ResponseEntity.ok().body(widgetService.remove(Long.parseLong(id)));
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
    //     Map<String, String> validationErrors = new HashMap<>();
    //     ex.getBindingResult().getAllErrors().forEach((error) -> {
    //         String fieldName = ((FieldError) error).getField();
    //         String errorMessage = error.getDefaultMessage();
    //         validationErrors.put(fieldName, errorMessage);
    //     });

    //     WidgetResponseError widgetResponseError = new WidgetResponseError(validationErrors);

    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(widgetResponseError.build());
    // }
}
