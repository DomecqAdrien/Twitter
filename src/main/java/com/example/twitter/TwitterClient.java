package com.example.twitter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class TwitterClient {

        
        public static void main(String[] args) throws IOException, InterruptedException {
                HttpClient httpClient = HttpClient.newHttpClient();
                launchHelloWorld(httpClient);
                postSample(httpClient);
                postSample(httpClient);
                postSample(httpClient);
                getPost(httpClient);
        }
        
        private static void getPost(HttpClient httpClient) throws IOException, InterruptedException {
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/post/2"))
            .GET()
            .build();
            
            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            
            System.out.println(response.body());
    }
        
        private static void postSample(HttpClient httpClient) throws IOException, InterruptedException {
            String body ="{\"authorid\":0,\"message\":\"HelloWorld\",\"origin\":0}";
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/post"))
            .POST(BodyPublishers.ofString(body))
            .build();
            
            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            
            System.out.println(response.body());
    }

        private static void launchHelloWorld(HttpClient httpClient) throws IOException, InterruptedException {
                HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080"))
                .GET()
                .build();
                
                HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
                
                System.out.println(response.body());
        }
}