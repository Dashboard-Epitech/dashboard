package com.dashboard.api.Service;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.Currencie;
import com.dashboard.api.Request.CurrencyRequest;
import com.dashboard.api.Request.WidgetRequest;

@Service
public class CurrencieService extends WidgetService {

    @Value("${CURRENCIES_API_USERNAME}")
    private String API_USERNAME;

    @Value("${CURRENCIES_API_PASSWORD}")
    private String API_PASSWORD;

    private final static String API_URL = "https://xecdapi.xe.com/v1/";
    private final static String API_URL_ALL_CURRENCIES = "currencies.json/?obsolete=false";
    private final static String API_URL_CONVERT_CURRENCI = "convert_from.json/";

    @Override
    public Object createWidget() {
        Currencie currencie = new Currencie();

        widgetRepository.save(currencie);

        return currencie;
    }

    @Override
    public <W extends WidgetRequest> Object updateWidget(int id, W request) throws Exception {
        Currencie currency = super.getInstanceOf(Currencie.class, id);
        CurrencyRequest currencyRequest = (CurrencyRequest) request;

        currency.setFromCurrency(currencyRequest.getFrom());

        if (currencyRequest.getTo().isBlank())
            currency.setToCurrencies(currencyRequest.getFrom());
        else
            currency.setToCurrencies(currencyRequest.getTo());

        widgetRepository.save(currency);

        return currency;
    }

    @Override
    public String updateData(int id) throws Exception {
        Currencie currencie = super.getInstanceOf(Currencie.class, id);

        if (currencie.getFromCurrency() == null || currencie.getToCurrencies() == null)
            throw new Exception("Not currencie");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(
                        API_URL + API_URL_CONVERT_CURRENCI + "?from="
                                + currencie.getFromCurrency()
                                + "&to=" + currencie.getToCurrencies()))
                .build();

        HttpClient httpClient = this.getClient();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        return response.body();
    }

    public String getAllCurrencie() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(
                        API_URL + API_URL_ALL_CURRENCIES))
                .build();

        HttpClient httpClient = this.getClient();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        return response.body();
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
