package com.dashboard.api.Entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("Currencie")
public class Currencie extends Widget {

    @Getter
    private final static String API_USERNAME = "epitech578924778";
    @Getter
    private final static String API_PASSWORD = "536vr5pmbh3o2smscmbsd074t";
    @Getter
    private final static String API_URL = "https://xecdapi.xe.com/v1/";
    @Getter
    private final static String API_URL_ALL_CURRENCIES = "currencies.json/?obsolete=false";
    @Getter
    private final static String API_URL_CONVERT_CURRENCI = "convert_from.json/";

    private String currencie1;
    private String currencie2;
}
