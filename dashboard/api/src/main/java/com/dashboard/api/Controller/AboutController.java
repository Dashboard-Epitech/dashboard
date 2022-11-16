package com.dashboard.api.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Service.AboutService;

@RestController
public class AboutController {

    @Autowired
    AboutService aboutService;

    @GetMapping(path = "/about")
    public Object toJSONObject(HttpServletRequest request) {
        return this.aboutService.toJson(request);
    }
}
