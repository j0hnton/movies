package com.moringaschool.showflix;

import android.util.Log;

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

import static com.moringaschool.showflix.MoiveDisplay.TAG;

public class MovieService {
    public static void findMovie(String search, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MOVIE_SEARCH_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.MOVIE_SEARCH_QUERY_PARAMETER, search)
                .addQueryParameter(Constants.MOVIE_TOKEN_QUERY_PARAMETER, Constants.MOVIE_TOKEN);
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()

                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Movie> processResults(Response response) {
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            if (response.isSuccessful()) {
                String jsonData = response.body().string();
                Log.d(TAG, "processResults: " + jsonData);
                JSONObject movieJSON = new JSONObject(jsonData);
                JSONArray searchesJSON = movieJSON.getJSONArray("results");

                for (int i = 0; i < searchesJSON.length(); i++) {
                    JSONObject searchJSON = searchesJSON.getJSONObject(i);
                    String name = searchJSON.getString("title");
                    String overview = searchJSON.getString("overview");
                    String release_date = searchJSON.getString("release_date");
String image=searchJSON.getString("poster_path");
String rate=searchJSON.getString("popularity");

                    Movie movie = new Movie(name, release_date, overview,image,rate);
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