package com.dashboard.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dashboard.api.Request.CurrencyRequest;
import com.dashboard.api.Service.CurrencyService;

@Controller
@RequestMapping("widget/currency")
public class CurrencyController extends WidgetContoller {

    @Autowired
    CurrencyService currencyService;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Object createWidget() {
        return super.createWidget(this.currencyService);
    }

    @RequestMapping(path = "/update/field/{id}", method = RequestMethod.POST)
    public Object updateCurrencies(@PathVariable(value = "id") String id, @RequestBody CurrencyRequest request) {
        return super.updateWidget(Integer.parseInt(id), request, this.currencyService);
    }

    @RequestMapping(path = "/update/{id}")
    public Object UpdateDataWidget(@PathVariable(value = "id") String id) {
        return super.updateData(Integer.parseInt(id), this.currencyService);
    }

    @RequestMapping(path = "/getAll")
    public Object getAllCurrencies() {
        try {
            return this.currencyService.getAllCurrencies();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
