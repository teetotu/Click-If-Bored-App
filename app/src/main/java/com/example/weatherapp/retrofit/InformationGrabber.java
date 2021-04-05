package com.example.weatherapp.retrofit;

import com.example.weatherapp.datamodel.articles.ArticleSearch;
import com.example.weatherapp.datamodel.movie.MovieResponse;
import com.example.weatherapp.datamodel.trivia.TriviaQa;
import com.example.weatherapp.datamodel.weather.WeatherResult;

import java.util.List;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InformationGrabber {
    @GET("weather?")
    Observable<WeatherResult> getWeatherByCoordinates(@Query("lat") String lat,
                                                      @Query("lon") String lon,
                                                      @Query("appid") String api_key,
                                                      @Query("units") String unit);

    @GET("arts.json?")
    Observable<ArticleSearch> getTopArticleNytArts(@Query("api-key") String api_key);

    @GET("science.json?")
    Observable<ArticleSearch> getTopArticleNytScience(@Query("api-key") String api_key);

    @GET("random")
    Observable<List<TriviaQa>> getTriviaQAndA();

    @GET("movie/popular")
    Observable<MovieResponse> getMovie(@Query("api_key") String api_key);
}
