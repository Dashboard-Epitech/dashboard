package com.dashboard.api.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dashboard.api.Error.WidgetResponseError;
import com.dashboard.api.Request.CurrencyRequest;
import com.dashboard.api.Service.CurrencyService;

@Controller
@RequestMapping("widget/currency")
public class CurrencyController extends WidgetContoller {

    @Autowired
    CurrencyService currencyService;

    @RequestMapping(path = "/create")
    public ResponseEntity<?> createWidget() {
        return super.createWidget(this.currencyService);
    }

    @RequestMapping(path = "/update/field/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateCurrencies(@PathVariable(value = "id") String id,
            @RequestBody @Valid CurrencyRequest request) {
        return super.updateWidget(Integer.parseInt(id), request, this.currencyService);
    }

    @RequestMapping(path = "/update/{id}")
    public ResponseEntity<?> UpdateDataWidget(@PathVariable(value = "id") String id) {
        return super.updateData(Integer.parseInt(id), this.currencyService);
    }

    @RequestMapping(path = "/getAll")
    public ResponseEntity<?> getAllCurrencies() {
        try {
            return ResponseEntity.ok().body(this.currencyService.getAllCurrencies());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
