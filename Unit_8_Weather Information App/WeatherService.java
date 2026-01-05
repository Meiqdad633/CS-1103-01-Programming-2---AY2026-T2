package com.weatherapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.*;
import java.net.URI;

public class WeatherService {
    private final String apiKey;
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public WeatherService(String apiKey) {
        this.apiKey = apiKey;
    }

    public JsonNode fetchCurrent(String city, String units) throws Exception {
        String url = String.format(
            "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=%s",
            city, apiKey, units
        );
        HttpRequest req = HttpRequest.newBuilder(URI.create(url)).build();
        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        if (res.statusCode() != 200) throw new RuntimeException("API error: " + res.statusCode());
        return mapper.readTree(res.body());
    }
}
