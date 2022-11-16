package com.dashboard.api.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;

@Service
public class AboutService {

    public Object toJson(HttpServletRequest request) {
        JSONObject out = new JSONObject();
        JSONObject client = new JSONObject();
        JSONObject server = new JSONObject();
        JSONArray services = new JSONArray();

        String ip = request.getRemoteAddr();

        client.put("host", ip);

        services.add(
                newService(
                        "Weather",
                        newWidget(
                                "weather",
                                "displays the city with the temperrature as well as the weather",
                                newParam(
                                        "city",
                                        "String"))));
        services.add(
                newService(
                        "Currency",
                        newWidget(
                                "currency",
                                "Compares two currencies",
                                newParam(
                                        "from",
                                        "String"),
                                newParam(
                                        "to",
                                        "String"))));
        services.add(
                newService(
                        "Spotify",
                        newWidget(
                                "playlist",
                                "user playlist",
                                newParam(
                                        "playlist",
                                        "String")),
                        newWidget(
                                "random_tract",
                                "random aminem song")));
        services.add(newService("github"));
        services.add(
                newService(
                        "youtube",
                        newWidget(
                                "channel",
                                "displays a channel name, a numbers of suscriber, number of view",
                                newParam(
                                        "channel",
                                        "String"))));

        server.put("current_time", System.currentTimeMillis());
        server.put("services", services);

        out.put("client", client);
        out.put("server", server);

        return out;
    }

    private Object newService(String name, Object... widget) {
        JSONObject out = new JSONObject();
        out.put("name", name);
        out.put("widgets", widget);

        return out;
    }

    private Object newWidget(String name, String desc, Object... param) {
        JSONObject out = new JSONObject();
        out.put("name", name);
        out.put("description", desc);
        out.put("params", param);

        return out;
    }

    private Object newParam(String name, String type) {
        JSONObject out = new JSONObject();
        out.put("name", name);
        out.put("type", type);

        return out;
    }
}
