package com.dashboard.api.Entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Currencie")
public class Currencie extends Widget {
    private final static String API_USERNAME = "epitech578924778";
    private final static String API_PASSWORD = "536vr5pmbh3o2smscmbsd074t";
    private final static String API_URL = "https://xecdapi.xe.com/v1/";
    private final static String API_URL_ALL_CURRENCIES = "currencies.json/?obsolete=false";
    private final static String API_URL_CONVERT_CURRENCI = "convert_from.json/";

    private String currencie1;
    private String currencie2;

    public String getCurrencie1() {
        return currencie1;
    }

    public void setCurrencie1(String currencie1) {
        this.currencie1 = currencie1;
    }

    public String getCurrencie2() {
        return currencie2;
    }

    public void setCurrencie2(String currencie2) {
        this.currencie2 = currencie2;
    }

    public static String getApiUrl() {
        return API_URL;
    }

    public static String getApiUrlAllCurrencies() {
        return API_URL_ALL_CURRENCIES;
    }

    public static String getApiUrlConvertCurrenci() {
        return API_URL_CONVERT_CURRENCI;
    }

    public static String getApiUsername() {
        return API_USERNAME;
    }

    public static String getApiPassword() {
        return API_PASSWORD;
    }
}
