package android.project.spotitop.data.api;

import android.project.spotitop.data.api.serialization.PlayistResponse;
import android.project.spotitop.data.api.serialization.Track;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TopSongsDisplayService {
    /**
     * Get the global top 50 songs playist from spotify (id of the playist : 37i9dQZEVXbMDoHDwVN2tF)
     * And return a TopSongsResponse object which contains the useful infos of the JSON response from the Spotify API
     */
    @GET("/playlists/{playist_id}}")
    Single<PlayistResponse> getTopTracks(@Path("playlist_id") String playist_id, @Query("key") String apikey);

    @GET("/tracks/5QyvmZYiOuK0saTv6J77Yg")
    // todo : changer pour @GET("/tracks/{track_id}}")
    Single<Track> getTrackDetails(@Path("track_id") String track_id, @Query("key") String apikey);
}
