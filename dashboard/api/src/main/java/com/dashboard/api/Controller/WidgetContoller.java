package com.dashboard.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Repository.WidgetRepository;

@RestController
@RequestMapping(path = "/widget")
public class WidgetContoller {
    @Autowired
    WidgetRepository widgetRepository;
}
