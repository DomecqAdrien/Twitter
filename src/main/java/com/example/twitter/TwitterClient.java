package com.example.twitter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONObject;

public class TwitterClient {

        
        public static void main(String[] args) throws IOException, InterruptedException {
                HttpClient httpClient = HttpClient.newHttpClient();
                register(httpClient, "fabinou");
                Long userId = login(httpClient, "fabinou");
                Long postId = createPost(httpClient, "Hello World", 0, userId);
                getPost(httpClient, postId);
                createPost(httpClient, "pls answer", postId, userId);
                getHistorique(httpClient, userId);
                getTimeline(httpClient, userId);
                Long bergaUserId = getUser(httpClient, 4);
                follow(httpClient, bergaUserId, userId);
                getTimeline(httpClient, userId);
                getUser(httpClient, userId);
                fav(httpClient, userId, postId);
                getUser(httpClient, userId);
                repost(httpClient, userId, postId);
                getUser(httpClient, userId);
        }
        
        private static Long getUser(HttpClient httpClient, long userId) throws IOException, InterruptedException {
        	HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/user/"+userId)).GET().build();
            HttpResponse < String > response = httpClient.send(request, BodyHandlers.ofString());
            System.out.println("user: "+response.body());
            JSONObject jsonObject = new JSONObject(response.body());
            return jsonObject.getLong("id");
		}

		private static void register(HttpClient httpClient, String name) throws IOException, InterruptedException {
            String body = "{\"username\":\"" + name + "\"}";
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/user/register")).POST(HttpRequest.BodyPublishers.ofString(body)).header("Content-type", "application/json").build();
            HttpResponse < String > response = httpClient.send(request, BodyHandlers.ofString());
            System.out.println("register: "+response.body());
        }
        
        private static Long login(HttpClient httpClient, String name) throws IOException, InterruptedException { 		
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/user/login/"+name)).GET().build();
            HttpResponse < String > response = httpClient.send(request, BodyHandlers.ofString());
            System.out.println("login: "+response.body());
            JSONObject jsonObject = new JSONObject(response.body());
            
            return jsonObject.getLong("id");
        }
        
        private static Long createPost(HttpClient httpClient, String message, long postId, Long userId) throws IOException, InterruptedException {
            String body = "{\"message\":\"" + message + "\",\"origin\":" + postId + "}";
            HttpRequest request = HttpRequest.newBuilder()
            		.uri(URI.create("http://localhost:8080/post"))
            		.POST(HttpRequest.BodyPublishers.ofString(body))
            		.header("Content-type", "application/json")
            		.setHeader("Cookie", "userid=" + userId).build();
            HttpResponse < String > response = httpClient.send(request, BodyHandlers.ofString());
            System.out.println("new post: "+response.body());
            JSONObject jsonObject = new JSONObject(response.body());
            return jsonObject.getLong("id");
        }
        
        private static void getPost(HttpClient httpClient, long postId) throws IOException, InterruptedException {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/post/" + postId)).GET().build();
            HttpResponse < String > response = httpClient.send(request, BodyHandlers.ofString());
            System.out.println("post: "+response.body());
        }
        
        private static void getHistorique(HttpClient httpClient, Long authorId) throws IOException, InterruptedException {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/historique/" + authorId)).GET().build();
            HttpResponse < String > response = httpClient.send(request, BodyHandlers.ofString());
            System.out.println("historique: "+response.body());
        }
        
        private static void getTimeline(HttpClient httpClient, Long userId) throws IOException, InterruptedException {
            HttpRequest request = HttpRequest.newBuilder()
            		.uri(URI.create("http://localhost:8080/timeline"))
            		.GET().setHeader("Cookie", "userid=" + userId).build();
            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            System.out.println("Timeline: "+response.body());
        }
        
        
        private static void follow(HttpClient httpClient, Long bergaUserId, Long userId) throws IOException, InterruptedException {
            HttpRequest request = HttpRequest.newBuilder()
            		.uri(URI.create("http://localhost:8080/user/follow/" + bergaUserId))
            		.GET().setHeader("Cookie", "userid=" + userId).build();
            HttpResponse < String > response = httpClient.send(request, BodyHandlers.ofString());
            System.out.println("now following: "+response.body());
        }
        
        private static void fav(HttpClient httpClient, Long userId, Long postId) throws IOException, InterruptedException {
            HttpRequest request = HttpRequest.newBuilder()
            		.uri(URI.create("http://localhost:8080/fav/" + postId))
            		.GET().setHeader("Cookie", "userid=" + userId).build();
            HttpResponse < String > response = httpClient.send(request, BodyHandlers.ofString());
            System.out.println("fav tweet: "+response.body());
        }
        
        private static void repost(HttpClient httpClient, Long userId, Long postId) throws IOException, InterruptedException {
            HttpRequest request = HttpRequest.newBuilder()
            		.uri(URI.create("http://localhost:8080/repost/" + postId))
            		.GET().setHeader("Cookie", "userid=" + userId).build();
            HttpResponse < String > response = httpClient.send(request, BodyHandlers.ofString());
            System.out.println("repost tweet: "+response.body());
        }
        
        
}