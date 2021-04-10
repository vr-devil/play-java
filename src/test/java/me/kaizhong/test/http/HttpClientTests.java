package me.kaizhong.test.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientTests {

    @Test
    public void testGet() {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(60))
                .build();

        HttpRequest req = HttpRequest.newBuilder(URI.create("https://docs.oracle.com/")).build();


        try {
            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals(200, res.statusCode());
            Assertions.assertTrue(StringUtils.isNotBlank(res.body()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
