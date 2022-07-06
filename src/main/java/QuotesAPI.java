import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class QuotesAPI {
    private final String URL = "https://api.quotable.io";
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public void intAPIQuotes() throws ExecutionException, InterruptedException, TimeoutException, JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        sb.append(URL).append("/random");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.valueOf(sb)))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        CompletableFuture<HttpResponse<String>> response =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(result, Map.class);
        Quotes quotes = objectMapper.convertValue(map, Quotes.class);
        System.out.println(quotes.getContent());
        System.out.println(quotes.getAuthor());
    }
}
