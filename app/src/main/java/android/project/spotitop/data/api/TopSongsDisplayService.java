package android.project.spotitop.data.api;

import android.project.spotitop.data.api.serialization.AuthorizationResponse;
import android.project.spotitop.data.api.serialization.PlayistResponse;
import android.project.spotitop.data.api.serialization.Track;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface TopSongsDisplayService {
    /**
     * Get the global top 50 songs playist from spotify (id of the playist : 37i9dQZEVXbMDoHDwVN2tF)
     * And return a TopSongsResponse object which contains the useful infos of the JSON response from the Spotify API
     */
    @GET("/v1/playlists/{playlist_id}")
    Single<PlayistResponse> getTopTracks(@Path("playlist_id") String playlist_id, @Header("Authorization") String tokenBearer);

    //
    @GET("/tracks/{track_id}")
    Single<Track> getTrackDetails(@Path("track_id") String track_id, @Header("Authorization") String tokenBearer);


    @FormUrlEncoded
    @POST("https://accounts.spotify.com/api/token")
    Single<AuthorizationResponse> getAuthorizationToken(@Header("Authorization") String authHeader, @Field("grant_type") String grantType);
}
