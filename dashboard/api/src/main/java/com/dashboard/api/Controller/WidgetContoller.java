package com.dashboard.api.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Request.WidgetRequest;
import com.dashboard.api.Service.WidgetService;

@RestController
@RequestMapping(path = "/widget")
public abstract class WidgetContoller {

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
}
