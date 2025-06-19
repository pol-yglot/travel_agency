package com.example.demo.service.impl;

import com.example.demo.dto.FlightResponseDTO;
import com.example.demo.dto.FlightSearchDTO;
import com.example.demo.service.AmadeusApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AmadeusApiServiceImpl implements AmadeusApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${amadeus.api.clientId}")
    private String clientId;

    @Value("${amadeus.api.clientSecret}")
    private String clientSecret;

    @Value("${amadeus.api.url.token}")
    private String tokenUrl;

    @Value("${amadeus.api.url.search}")
    private String searchUrl;

    private String accessToken;
    private long tokenExpirationTime; // 추가됨

    @Override
    public ResponseEntity<List<FlightResponseDTO>> searchFlights(FlightSearchDTO dto) {
        try {
            if (accessToken == null || System.currentTimeMillis() >= tokenExpirationTime) {
                accessToken = getAccessToken();
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            String url = String.format("%s?originLocationCode=%s&destinationLocationCode=%s&departureDate=%s&adults=%d",
                    searchUrl, dto.getOrigin(), dto.getDestination(), dto.getDepartureDate(), dto.getAdults());

            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            List<Map<String, Object>> dataList = (List<Map<String, Object>>) response.getBody().get("data");

            List<FlightResponseDTO> result = new ArrayList<>();
            for (Map<String, Object> item : dataList) {
                Map<String, Object> itinerary = ((List<Map<String, Object>>) item.get("itineraries")).get(0);
                List<Map<String, Object>> segments = (List<Map<String, Object>>) itinerary.get("segments");
                Map<String, Object> firstSegment = segments.get(0);
                Map<String, Object> lastSegment = segments.get(segments.size() - 1);

                String airline = (String) firstSegment.get("carrierCode");
                Map<String, Object> departureInfo = (Map<String, Object>) firstSegment.get("departure");
                Map<String, Object> arrivalInfo = (Map<String, Object>) lastSegment.get("arrival");

                String departure = departureInfo.get("iataCode").toString();
                String departureTime = departureInfo.get("at").toString();
                String arrival = arrivalInfo.get("iataCode").toString();
                String arrivalTime = arrivalInfo.get("at").toString();
                String price = ((Map<String, Object>) item.get("price")).get("total").toString();

                FlightResponseDTO dtoItem = new FlightResponseDTO();
                dtoItem.setAirline(airline);
                dtoItem.setDeparture(departure);
                dtoItem.setArrival(arrival);
                dtoItem.setDepartureTime(departureTime);
                dtoItem.setArrivalTime(arrivalTime);
                dtoItem.setPrice(price);

                result.add(dtoItem);
            }

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            log.error("Flight search error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private String getAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret;

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, entity, Map.class);

        Map<String, Object> responseBody = response.getBody();
        this.tokenExpirationTime = System.currentTimeMillis() + ((Integer) responseBody.get("expires_in")) * 1000L;
        this.accessToken = responseBody.get("access_token").toString();
        return this.accessToken;
    }
}
