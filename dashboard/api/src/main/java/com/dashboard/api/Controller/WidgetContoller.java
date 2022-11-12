package com.dashboard.api.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Request.WidgetRequest;
import com.dashboard.api.Service.WidgetService;

@RestController
@RequestMapping(path = "/widget")
public abstract class WidgetContoller {

    public Object createWidget(WidgetService service) {
        return service.createWidget();
    }

    public Object updateWidget(int id, WidgetRequest request, WidgetService service) {
        try {
            return service.updateWidget(id, request);
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
