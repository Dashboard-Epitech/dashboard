package com.dashboard.api.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Service.WidgetService;

@RestController
@RequestMapping(path = "/widget")
public abstract class WidgetContoller {

    public Object createWidget(String body, WidgetService service) {
        return service.createWidget(body);
    }

    public Object updateWidget(int id, String body, WidgetService service) {
        try {
            return service.updateWidget(id, body);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Object updateData(int id, WidgetService service) {
        try {
            return service.updateData(id);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
