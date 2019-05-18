package com.moringaschool.showflix;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MovieService {
    public static void findMovie(String search, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder= HttpUrl.parse(Constants.MOVIE_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.MOVIE_SEARCH_QUERY_PARAMETER, search);
        String url = urlBuilder.build().toString();
        Request request=new Request.Builder()

                .url(url)
                .header("Authorization",Constants.MOVIE_TOKEN).build();
        Call call=client.newCall(request);
        call.enqueue(callback);
    }
}



