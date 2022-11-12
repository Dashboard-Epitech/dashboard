package com.dashboard.api.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Error.WidgetResponseError;
import com.dashboard.api.Request.WidgetRequest;
import com.dashboard.api.Service.WidgetService;

@RestController
@RequestMapping(path = "/widget")
public class WidgetContoller {

    @Autowired
    WidgetService widgetService;

    public ResponseEntity<?> createWidget(WidgetService service) {
        return ResponseEntity.ok().body(service.createWidget());
    }

    public ResponseEntity<?> updateWidget(int id, WidgetRequest request, WidgetService service) {
        try {
            return ResponseEntity.ok().body(service.updateWidget(id, request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<?> updateData(int id, WidgetService service) {
        try {
            return ResponseEntity.ok().body(service.updateData(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> remove(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(widgetService.remove(Integer.parseInt(id)));
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

        WidgetResponseError weatherResponseError = new WidgetResponseError(validationErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(weatherResponseError.build());
    }
}
