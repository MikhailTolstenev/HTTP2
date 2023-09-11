package ru.netology;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static CloseableHttpClient createHttp() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
        return httpClient;
    }

    public static final String REMOTE_SERVICE_UR = "https://api.nasa.gov/planetary/apod?api_key=73k5uAGkIqvmWCXW9J8XRGodF6LJpesVPqGXXmO1";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = createHttp();
        HttpGet request = new HttpGet(REMOTE_SERVICE_UR);
        CloseableHttpResponse response = httpClient.execute(request);
        InputStream content = response.getEntity().getContent();
        Post post = null;
        post = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
        });
        CloseableHttpClient httpClient2 = createHttp();
        HttpGet request2 = new HttpGet(post.getUrl());
        System.out.println(post.getUrl());
        File file = new File(post.getUrl());
        request2.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        CloseableHttpResponse response2 = httpClient2.execute(request2);
        InputStream content2 = response2.getEntity().getContent();
        FileOutputStream fos = new FileOutputStream(file.getName());
        byte[] buffer = content2.readAllBytes();
        fos.write(buffer, 0, buffer.length);
        System.out.println("Файл Записан!");


    }


}
