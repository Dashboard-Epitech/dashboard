package com.dashboard.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dashboard.api.Service.CurrencieService;

@Controller
@RequestMapping("widget/currencie")
public class CurrencieController extends WidgetContoller {

    @Autowired
    CurrencieService currencieService;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Object createWidget(@RequestBody String body) {
        return super.createWidget(body, this.currencieService);
    }

    @RequestMapping(path = "/updateCurrencies/{id}", method = RequestMethod.POST)
    public Object updateCurrencies(@PathVariable(value = "id") String id, @RequestBody String body) {
        return super.updateWidget(Integer.parseInt(id), body, this.currencieService);
    }

    @RequestMapping(path = "/updateData/{id}")
    public Object UpdateDataWidget(@PathVariable(value = "id") String id) {
        return super.updateData(Integer.parseInt(id), this.currencieService);
    }

    @RequestMapping(path = "/getAll")
    public Object getAllCurrencie() {
        try {
            return this.currencieService.getAllCurrencie();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
