package android.project.spotitop.data.api;

import android.project.spotitop.data.api.serialization.AuthorizationResponse;
import android.project.spotitop.data.api.serialization.TopTracksResponse;
import android.project.spotitop.data.api.serialization.Track;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TopSongsDisplayService {
    /**
     * Get the global top 50 songs playist from Spotify (id of the playist : 37i9dQZEVXbMDoHDwVN2tF)
     * And return a TopSongsResponse object which contains the useful infos of the JSON response from the Spotify API
     * @param playlist_id : the id of the playist
     * @param tokenBearer : the token used to authentificate to the api
     * @return a TopTrackResponse object
     */
    @GET("/v1/playlists/{playlist_id}")
    Single<TopTracksResponse> getTopTracks(@Path("playlist_id") String playlist_id, @Header("Authorization") String tokenBearer);

    /**
     * Get useful details about a track from the Spotify API
     * @param track_id : the id of the track
     * @param tokenBearer : the token used to authentificate to the API
     * @return a Track object
     */
    @GET("/v1/tracks/{track_id}")
    Single<Track> getTrackDetails(@Path("track_id") String track_id, @Header("Authorization") String tokenBearer);


    /**
     * Ask for an access token to use the Spotify API
     * @param authHeader : the key to authentificate the user (base64 string from user id and user secret
     * @param grantType : value is "client_credentials"
     * @return
     */
    @FormUrlEncoded
    @POST("https://accounts.spotify.com/api/token")
    Single<AuthorizationResponse> getAuthorizationToken(@Header("Authorization") String authHeader, @Field("grant_type") String grantType);
}
