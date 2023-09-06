package ru.netology;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static CloseableHttpClient createHttp() throws IOException {
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
    }
    public static final String REMOTE_SERVICE_UR ="https://api.nasa.gov/planetary/apod?api_key=73k5uAGkIqvmWCXW9J8XRGodF6LJpesVPqGXXmO1";
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = createHttp();

        HttpGet request = new HttpGet(REMOTE_SERVICE_UR);
        CloseableHttpResponse response = httpClient.execute(request);

        InputStream content = response.getEntity().getContent();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Post> posts = new ArrayList<>();
        posts.add(objectMapper.readValue(content, new TypeReference<>() {
        }));
        posts.forEach(System.out::println);

    }
}