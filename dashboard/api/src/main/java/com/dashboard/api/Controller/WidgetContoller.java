package com.dashboard.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
