package com.dashboard.api.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dashboard.api.Service.CurrencieService;

@Controller
@RequestMapping("widget/currencie")
public class CurrencieController extends WidgetContoller {

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Object createWidget(@RequestBody String body, CurrencieService currencieService) {
        return super.createWidget(body, currencieService);
    }

    @RequestMapping(path = "/updateCurrencies/{id}", method = RequestMethod.POST)
    public Object updateCurrencies(@PathVariable(value = "id") String id, @RequestBody String body,
            CurrencieService currencieService) {
        return super.updateWidget(Integer.parseInt(id), body, currencieService);
    }

    @RequestMapping(path = "/updateData/{id}")
    public Object UpdateDataWidget(@PathVariable(value = "id") String id, CurrencieService currencieService) {
        return super.updateData(Integer.parseInt(id), currencieService);
    }

    @RequestMapping(path = "/getAll")
    public Object getAllCurrencie(CurrencieService currencieService) {
        try {
            return currencieService.getAllCurrencie();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
