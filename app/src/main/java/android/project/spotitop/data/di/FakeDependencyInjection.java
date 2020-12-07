package android.project.spotitop.data.di;

import android.content.Context;
import android.project.spotitop.data.api.TopSongsDisplayService;
import android.project.spotitop.data.database.TopSongDatabase;
import android.project.spotitop.data.repository.topsongsdisplay.TopSongsDisplayDataRepository;
import android.project.spotitop.data.repository.topsongsdisplay.TopSongsDisplayRepository;
import android.project.spotitop.data.repository.topsongsdisplay.local.TopSongsDisplayLocalDataSource;
import android.project.spotitop.data.repository.topsongsdisplay.mapper.TrackToTrackEntityMapper;
import android.project.spotitop.data.repository.topsongsdisplay.remote.TopSongsDisplayRemoteDataSource;
import android.project.spotitop.presentation.viewmodel.ViewModelFactory;

import androidx.room.Room;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FakeDependencyInjection {
    private static TopSongsDisplayService topSongsDisplayService;
    private static Retrofit retrofit;
    private static Gson gson;
    private static TopSongsDisplayRepository topSongsDisplayRepository;
    private static TopSongDatabase topSongDatabase;
    private static Context applicationContext;
    private static ViewModelFactory viewModelFactory;

    public static ViewModelFactory getViewModelFactory() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelFactory(getTopSongsDisplayRepository());
        }
        return viewModelFactory;
    }


    public static TopSongsDisplayRepository getTopSongsDisplayRepository() {
        if (topSongsDisplayRepository == null) {
            topSongsDisplayRepository = new TopSongsDisplayDataRepository(
                    new TopSongsDisplayRemoteDataSource(getTopSongsDisplayService()),
                    new TopSongsDisplayLocalDataSource(getTopSongDatabase()),
                    new TrackToTrackEntityMapper()
            );
        }
        return topSongsDisplayRepository;
    }

    public static TopSongsDisplayService getTopSongsDisplayService() {
        if (topSongsDisplayService == null) {
            topSongsDisplayService = getRetrofit().create(TopSongsDisplayService.class);
        }
        return topSongsDisplayService;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.spotify.com/v1/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }

    public static TopSongDatabase getTopSongDatabase() {
        if (topSongDatabase == null) {
            topSongDatabase = Room.databaseBuilder(applicationContext,
                    TopSongDatabase.class, "top-songs-database").build();
        }
        return topSongDatabase;
    }
}
