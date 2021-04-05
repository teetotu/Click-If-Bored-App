package com.example.weatherapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.example.weatherapp.common.Common;
import com.example.weatherapp.datamodel.articles.ArticleSearch;
import com.example.weatherapp.datamodel.movie.MovieResponse;
import com.example.weatherapp.datamodel.trivia.TriviaQa;
import com.example.weatherapp.retrofit.InformationGrabber;
import com.example.weatherapp.retrofit.RetrofitClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final String CLIENT_ID = "e9b6805a2c9044eeaaf49f62a6e2d424";
    private static final String REDIRECT_URI = "com.example.weatherapp://callback";
    private SpotifyAppRemote mSpotifyAppRemote;
    private ImageView img_logo;
    private ImageView img_poster;


    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;

    private Button activatorButton;
    private Button deactivatorButton;
    private ConstraintLayout triviaqa_panel;

    private ImageView img_weather;
    private WebView webView;

    private TextView txt_sunrise, txt_sunset, txt_pressure,
            txt_wind, txt_humidity, txt_city_name,
            txt_temperature, txt_date_time, txt_coordinates, txt_description, txt_title;

    private LinearLayout weather_panel;
    private ConstraintLayout movie_panel;
    private ProgressBar progressBar;

    private CompositeDisposable compositeDisposable;
    private InformationGrabber mService;

    private Button btn_clue, btn_answer;
    private TextView txt_question, txt_answer, txt_clue;

    @Override
    protected void onStart() {
        super.onStart();
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("MainActivity", "Connected! Yay!");
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("MainActivity", throwable.getMessage(), throwable);
                    }
                });

    }

    private void playSpotifyPlaylist() {
        // Then we will write some more code here.
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL");
        Log.d("SPOTIFY", "CONNECTED?");
        progressBar.setVisibility(View.GONE);
        img_logo.setVisibility(View.VISIBLE);

        deactivatorButton.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            buildLocationRequest();
                            buildLocationCallBack();
                            if (ActivityCompat.checkSelfPermission(MainActivity.this,
                                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                    && ActivityCompat.checkSelfPermission(
                                    MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
                            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                        Snackbar.make(MainActivity.this, "Permission denied", Snackbar.LENGTH_LONG);
                    }
                }).check();


        img_logo = findViewById(R.id.img_logo);
        img_poster = findViewById(R.id.img_poster);
        img_weather = findViewById(R.id.img_weather);
        txt_city_name = findViewById(R.id.txt_city_name);
        txt_coordinates = findViewById(R.id.txt_coordinates);
        txt_date_time = findViewById(R.id.txt_date_time);
        txt_temperature = findViewById(R.id.txt_temperature);
        txt_description = findViewById(R.id.txt_description);
        txt_title = findViewById(R.id.txt_title);
        txt_humidity = findViewById(R.id.txt_humidity);
        txt_pressure = findViewById(R.id.txt_pressure);
        txt_sunrise = findViewById(R.id.txt_sunrise_time);
        txt_sunset = findViewById(R.id.txt_sunset_time);
        txt_wind = findViewById(R.id.txt_wind);
        txt_coordinates = findViewById(R.id.txt_coordinates);
        webView = findViewById(R.id.webview);

        triviaqa_panel = findViewById(R.id.triviaqa_panel);

        btn_answer = findViewById(R.id.btn_answer);
        btn_answer.setOnClickListener(v -> {
            txt_answer.setVisibility(View.VISIBLE);
            txt_clue.setVisibility(View.GONE);
        });
        btn_clue = findViewById(R.id.btn_clue);
        btn_clue.setOnClickListener(v -> {
            txt_answer.setVisibility(View.GONE);
            txt_clue.setVisibility(View.VISIBLE);
        });
        txt_question = findViewById(R.id.txt_question);
        txt_answer = findViewById(R.id.txt_answer);
        txt_clue = findViewById(R.id.txt_clue);

        weather_panel = findViewById(R.id.weather_panel);
        movie_panel = findViewById(R.id.movie_panel);
        progressBar = findViewById(R.id.progress_bar);

        activatorButton = findViewById(R.id.btn_activator);
        deactivatorButton = findViewById(R.id.btn_deactivator);
        deactivatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setMessage("Are you sure?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
        activatorButton.setOnClickListener(v -> {
            Random rnd = new Random();
            PackageManager pm = getPackageManager();
            List<MethodRunner> functionality = new ArrayList<>();
            functionality.add(arg -> getWeatherInformation());
            functionality.add(arg -> getTopArticle());
            functionality.add(arg -> getTriviaQA());
            functionality.add(arg -> getMovieSuggestion());

            boolean isSpotifyInstalled;
            try {
                pm.getPackageInfo("com.spotify.music", 0);
                isSpotifyInstalled = true;
                Log.d("SPOTIFY", "INSTALLED");
                functionality.add(arg -> playSpotifyPlaylist());
            } catch (PackageManager.NameNotFoundException e) {
                isSpotifyInstalled = false;
                Log.d("SPOTIFY", "NOT INSTALLED");
            }

            int actionType = rnd.nextInt(functionality.size());
            functionality.get(actionType).run(new Object());
            activatorButton.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        });
    }

    private void buildLocationCallBack() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Common.current_location = locationResult.getLastLocation();

                Log.d("Location ",
                        locationResult.getLastLocation().getLatitude() + "/" + locationResult.getLastLocation().getLongitude());
            }
        };
    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationRequest.setInterval(5000);

        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10.0f);

    }

    private void getWeatherInformation() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance("https://api.openweathermap.org/data/2.5/");
        mService = retrofit.create(InformationGrabber.class);
        compositeDisposable.add(mService.getWeatherByCoordinates(
                String.valueOf(Common.current_location.getLatitude()),
                String.valueOf(Common.current_location.getLongitude()),
                Common.OWM_API_KEY, "metric").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherResult -> {
                    Picasso.get().load("https://openweathermap.org/img/w/" +
                            weatherResult.getWeather().get(0).getIcon() + ".png")
                            .into(img_weather);
                    if (weatherResult.getMain() != null) {
                        txt_city_name.setText(weatherResult.getName());
                        txt_description.setText(new StringBuilder
                                (("Weather in " + weatherResult.getName())));
                        txt_temperature.setText(new StringBuilder(
                                String.valueOf(weatherResult.getMain().getTemp())).append("°C"));
                        txt_date_time.setText(Common.convertUnixToDate(weatherResult.getDt()));
                        txt_pressure.setText(
                                new StringBuilder(String.valueOf(weatherResult.getMain().getPressure()))
                                        .append(" hpa"));
                        txt_wind.setText(
                                new StringBuilder("Speed: " + weatherResult.getWind().getSpeed() +
                                        " Angle: " + weatherResult.getWind().getDeg()));
                        txt_humidity.setText(
                                new StringBuilder(String.valueOf(weatherResult.getMain().getHumidity()))
                                        .append("%"));
                        txt_sunrise.setText(Common.convertUnixToHour(weatherResult.getSys().getSunrise()));
                        txt_sunset.setText(Common.convertUnixToHour(weatherResult.getSys().getSunset()));
                        txt_coordinates.setText(new StringBuilder(weatherResult.getCoord().toString()));
                    } else {
                        txt_city_name.setText("!!!!!!!!!!");
                        Log.d("Main", weatherResult.toString());
                    }

                    weather_panel.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }, throwable -> {
                    Log.d("Main", throwable.getMessage());
                }));

    }

    private void getTopArticle() {
        Random rnd = new Random();
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance("https://api.nytimes.com/svc/topstories/v2/");
        mService = retrofit.create(InformationGrabber.class);
        Consumer<Throwable> onError = throwable -> {
            Log.d("Main", throwable.getMessage());
        };
        Consumer<ArticleSearch> onSuccess = articleSearch -> {
            webView.loadUrl(articleSearch.getResults().get(rnd.nextInt(articleSearch.getResults().size() - 1)).getUrl());
            webView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        };

        int articleType = rnd.nextInt(2);
        switch (articleType) {
            case 0:
                compositeDisposable.add(mService.getTopArticleNytArts(
                        Common.NYT_API_KEY).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onSuccess, onError));
                break;
            case 1:
                compositeDisposable.add(mService.getTopArticleNytScience(
                        Common.NYT_API_KEY).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onSuccess, onError));
                break;
        }
    }

    private void getTriviaQA() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance("https://jservice.io/api/");
        mService = retrofit.create(InformationGrabber.class);
        Consumer<Throwable> onError = throwable -> {
            Log.d("Main", throwable.getMessage());
        };
        Consumer<List<TriviaQa>> onSuccess = triviaQa -> {
            txt_answer.setVisibility(View.GONE);
            txt_clue.setVisibility(View.GONE);
            txt_question.setText(triviaQa.get(0).getQuestion());
            txt_answer.setText(triviaQa.get(0).getAnswer());
            txt_clue.setText(triviaQa.get(0).getCategory().getTitle());
            triviaqa_panel.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        };

        compositeDisposable.add(mService.getTriviaQAndA()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, onError));
    }

    private void getMovieSuggestion() {
//        https://api.themoviedb.org/3/movie/550
        Random rnd = new Random();
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance("https://api.themoviedb.org/3/");
        mService = retrofit.create(InformationGrabber.class);
        Consumer<Throwable> onError = throwable -> {
            Log.d("Main", throwable.getMessage());
        };
        Consumer<MovieResponse> onSuccess = movieResponse -> {
            int movieNumber = rnd.nextInt(movieResponse.getResults().size()) - 1;
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" +
                    movieResponse.getResults().get(movieNumber).getPoster_path())
                    .into(img_poster);
            txt_title.setText(new StringBuilder("You should watch " +
                    movieResponse.getResults().get(movieNumber).getOriginal_title()));

            movie_panel.setVisibility(View.VISIBLE);
            //movieResponse.getResults().get(0)
            progressBar.setVisibility(View.GONE);
        };

        compositeDisposable.add(mService.getMovie(
                Common.TMDB_API_KEY).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, onError));
    }

    @Override
    protected void onStop() {
        super.onStop();
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }

    public interface MethodRunner {
        public void run(Object arg);
    }
}