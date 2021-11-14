package ru.kpfu.itis.summaryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author Roman Leontev
 * 18:34 14.11.2021
 * student ITIS
 */

@RestController
@RequestMapping("/search/film")
public class TmdbController {

    private static final String BASE_URL = "https://watchlater.cloud.technokratos.com";

    private static final String URL_TMDB_CONTROLLER = "https://watchlater.cloud.technokratos.com/search/film";

    private RestTemplate restTemplate;

    @Autowired
    public TmdbController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<String> getFilms(@RequestParam String query, @RequestParam(required = false) String maxRating, @RequestParam(required = false) String minRating, @RequestParam(required = false) String page) {

        String request = URL_TMDB_CONTROLLER + "?query=" + query;

        if (maxRating != null) {
            request += "max-rating="+ maxRating;
        }
        if (minRating != null) {
            request += "min-rating="+ minRating;
        }
        if (page != null) {
            request += "page="+ page;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(request, HttpMethod.GET, entity, String.class);
    }

}
