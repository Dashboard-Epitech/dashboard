package com.dashboard.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Repository.WidgetRepository;
import com.dashboard.api.Service.WidgetService;

@RestController
@RequestMapping(path = "/widget")
public abstract class WidgetContoller {
    @Autowired
    WidgetRepository widgetRepository;

    public Object createWidget(String body, WidgetService service) {
        return service.createWidget(body, this.widgetRepository);
    }

    public Object updateWidget(int id, String body, WidgetService service) {
        try {
            return service.updateWidget(id, body, this.widgetRepository);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Object updateData(int id, WidgetService service) {
        try {
            return service.updateData(id, this.widgetRepository);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
