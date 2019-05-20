package com.moringaschool.showflix;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieService {
    public static void findMovie(String search, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MOVIE_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.MOVIE_SEARCH_QUERY_PARAMETER, search);
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()

                .url(url)
                .header("Authorization", Constants.MOVIE_TOKEN).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Movie> processResults(Response response) {
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject movieJSON = new JSONObject(jsonData);
            JSONArray searchesJSON = movieJSON.getJSONArray("results");
            if (response.isSuccessful()) {
                for (int i = 0; i < searchesJSON.length(); i++) {
                    JSONObject searchJSON = searchesJSON.getJSONObject(i);
                    String name = movieJSON.getString("title");
                    String popularity = movieJSON.getString("popularity");
                    String release_date = movieJSON.getString("release_date");
                    String overview = movieJSON.getString("overview");

                    Movie movie = new Movie(name, popularity, release_date, overview);
                    movies.add(movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }
}