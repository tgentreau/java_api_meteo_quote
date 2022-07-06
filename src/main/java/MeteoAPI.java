import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInput;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MeteoAPI {
    private final String API_KEY = "8118ed6ee68db2debfaaa5a44c832918";
    private final String URL_FIRST_PART = "https://api.openweathermap.org/data/2.5/weather?q=";
    private final String URL_SECOND_PART = "&appid=" + API_KEY + "&units=metric&lang=fr";
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    private final String SAUT_LIGNE = "\n";
    private final String DEGRE = "°C";

    public void init(String city) throws ExecutionException, InterruptedException, TimeoutException, IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(URL_FIRST_PART).append(city).append(URL_SECOND_PART);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.valueOf(sb)))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();
        CompletableFuture<HttpResponse<String>> response =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();

        String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
        Map<String, Object> map = mapper.readValue(result, Map.class);
        Meteo meteo = mapper.convertValue(map, Meteo.class);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Voici la météo de ").append(meteo.getName()).append("\n")
                .append("Température actuelle: ").append(meteo.getMain().getTemp()).append(DEGRE).append(SAUT_LIGNE)
                .append("Température minimale de la journée: ").append(meteo.getMain().getTemp_min()).append(DEGRE).append(SAUT_LIGNE)
                .append("Température maximale de la journée: ").append(meteo.getMain().getTemp_max()).append(DEGRE).append(SAUT_LIGNE)
                .append("Pression: ").append(meteo.getMain().getPressure()).append(SAUT_LIGNE)
                .append("Humidité: ").append(meteo.getMain().getHumidity()).append(SAUT_LIGNE)
                .append("Levé du soleil: ").append(Date.from(Instant.ofEpochSecond((long) meteo.getSys().getSunrise()))).append(SAUT_LIGNE)
                .append("Couché du soleil: ").append(Date.from(Instant.ofEpochSecond((long) meteo.getSys().getSunset()))).append(SAUT_LIGNE);
        System.out.println(stringBuilder);
    }
}
