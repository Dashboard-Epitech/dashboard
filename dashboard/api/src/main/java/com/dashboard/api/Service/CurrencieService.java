package com.dashboard.api.Service;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.Currencie;
import com.dashboard.api.Repository.WidgetRepository;

@Service
public class CurrencieService extends WidgetService {

    @Override
    public Object createWidget(String body, WidgetRepository widgetRepository) {
        Currencie currencie = new Currencie();
        JSONObject input = new JSONObject(body);

        String currencie1 = input.getString("currencie1");
        String currencie2 = input.getString("currencie2");
        if (!currencie1.isBlank())
            currencie.setCurrencie1(currencie1);
        if (!currencie2.isBlank())
            currencie.setCurrencie2(currencie2);

        widgetRepository.save(currencie);

        return currencie;
    }

    @Override
    public Object updateWidget(int id, String body, WidgetRepository widgetRepository) throws Exception {
        Currencie currency = super.getInstanceOf(Currencie.class, id, widgetRepository);

        JSONObject input = new JSONObject(body);

        String currency1 = input.getString("currency1");
        String currency2 = input.getString("currency2");
        if (!currency1.isBlank())
            currency.setCurrencie1(currency1);

        if (!currency2.isBlank())
            currency.setCurrencie2(currency2);

        widgetRepository.save(currency);

        return currency;
    }

    @Override
    public String updateData(int id, WidgetRepository widgetRepository) throws Exception {
        Currencie currencie = super.getInstanceOf(Currencie.class, id, widgetRepository);

        if (currencie.getCurrencie1() == null || currencie.getCurrencie2() == null)
            throw new Exception("Not currencie");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(
                        Currencie.getAPI_URL() + Currencie.getAPI_URL_CONVERT_CURRENCI() + "?from="
                                + currencie.getCurrencie1()
                                + "&to=" + currencie.getCurrencie2()))
                .build();

        HttpClient httpClient = this.getClient();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        return response.body();
    }

    public String getAllCurrencie() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(
                        Currencie.getAPI_URL() + Currencie.getAPI_URL_ALL_CURRENCIES()))
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
                        return new PasswordAuthentication(Currencie.getAPI_USERNAME(),
                                Currencie.getAPI_PASSWORD().toCharArray());
                    }
                })
                .build();
    }
}
