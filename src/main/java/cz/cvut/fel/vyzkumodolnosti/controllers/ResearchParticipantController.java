package cz.cvut.fel.vyzkumodolnosti.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping(value = "/research-participant")
public class ResearchParticipantController {
    @Value("${vophp.api.accesstoken}")
    private String accessToken;
    @GetMapping(value = "/exists")
    public boolean checkExists(@RequestParam(required = true) String researchNumber) throws IOException, InterruptedException {
        String uri = "https://test.vyzkumodolnosti.cz/api/persons?codes=" + researchNumber;

        HttpClient client = HttpClient.newHttpClient();

        // Create HTTP request object
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .header("X-AUTH-TOKEN", accessToken)
                .header("Content-Type", "application/json")
                .build();
        // Send HTTP request
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return false;
    }
}
