package android.project.spotitop.data.repository.topsongsdisplay.remote;

import android.project.spotitop.SpotiTopApplication;
import android.project.spotitop.data.api.TopSongsDisplayService;
import android.project.spotitop.data.api.serialization.AuthorizationResponse;
import android.project.spotitop.data.api.serialization.PlayistResponse;
import android.project.spotitop.data.api.serialization.Track;
import android.util.Base64;

import io.reactivex.Single;

public class TopSongsDisplayRemoteDataSource {
    private TopSongsDisplayService topSongsDisplayService;

    public TopSongsDisplayRemoteDataSource(TopSongsDisplayService topSongsDisplayService) {
        this.topSongsDisplayService = topSongsDisplayService;
    }

    //id of the playist
    public Single<PlayistResponse> getDailyTopPlayistResponse(String tokenBearer) {
        return topSongsDisplayService.getTopTracks(SpotiTopApplication.ID_TOP_PLAYIST, tokenBearer);
    }

    public Single<Track> getTrackDetailsResponse(String id, String tokenBearer){
        return topSongsDisplayService.getTrackDetails(id, tokenBearer);
    }

    public Single<AuthorizationResponse> getAuthorizationResponse() {
        String baseAuth = SpotiTopApplication.CLIENT_ID + ":" + SpotiTopApplication.CLIENT_SECRET;
        String authHeader = "Basic " + Base64.encodeToString(baseAuth.getBytes(), Base64.NO_WRAP);
        String authentificationApi = "accounts.spotify.com/api/token";
        return topSongsDisplayService.getAuthorizationToken(authHeader, "client_credentials");
    }
}
