package com.dashboard.api.Service;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.Currency;
import com.dashboard.api.Entity.Widget;
import com.dashboard.api.Request.CurrencyRequest;
import com.dashboard.api.Request.WidgetRequest;

@Service
public class CurrencyService extends WidgetService {

    @Value("${CURRENCIES_API_USERNAME}")
    private String API_USERNAME;

    @Value("${CURRENCIES_API_PASSWORD}")
    private String API_PASSWORD;

    private final static String API_URL = "https://xecdapi.xe.com/v1/";
    private final static String API_URL_ALL_CURRENCIES = "currencies.json/?obsolete=false";
    private final static String API_URL_CONVERT_CURRENCI = "convert_from.json/";

    @Override
    public <W extends WidgetRequest> Object createWidget(W request) throws Exception {
        Currency currency = new Currency();

        return this.save(currency, (CurrencyRequest) request);
    }

    @Override
    public <W extends WidgetRequest> Object updateWidget(long id, W request) throws Exception {
        Currency currency = super.getInstanceOf(Currency.class, id);

        return this.save(currency, (CurrencyRequest) request);
    }

    @Override
    public String updateData(long id) throws Exception {
        Currency currency = super.getInstanceOf(Currency.class, id);

        if (currency.getFromCurrency() == null || currency.getToCurrencies() == null)
            throw new Exception("Not currency");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(
                        API_URL + API_URL_CONVERT_CURRENCI + "?from="
                                + currency.getFromCurrency()
                                + "&to=" + currency.getToCurrencies()))
                .build();

        HttpClient httpClient = this.getClient();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        return response.body();
    }

    public String getAllCurrencies() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(
                        API_URL + API_URL_ALL_CURRENCIES))
                .build();

        HttpClient httpClient = this.getClient();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        return response.body();
    }

    @Override
    protected <W extends Widget, R extends WidgetRequest> Object save(W currencyT, R requestT) throws Exception {
        Currency currency = (Currency) currencyT;
        CurrencyRequest request = (CurrencyRequest) requestT;

        currency.setFromCurrency(request.getFrom());

        if (request.getTo() == null || request.getTo().isBlank())
            currency.setToCurrencies(request.getFrom());
        else
            currency.setToCurrencies(request.getTo().replaceAll(" ", ""));

        return super.save(currency, request);
    }

    private HttpClient getClient() {
        return HttpClient.newBuilder()
                .authenticator(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(API_USERNAME,
                                API_PASSWORD.toCharArray());
                    }
                })
                .build();
    }
}
