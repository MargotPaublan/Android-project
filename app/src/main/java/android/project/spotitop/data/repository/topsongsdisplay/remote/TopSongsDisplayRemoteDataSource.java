package android.project.spotitop.data.repository.topsongsdisplay.remote;

import android.project.spotitop.SpotiTopApplication;
import android.project.spotitop.data.api.TopSongsDisplayService;
import android.project.spotitop.data.api.serialization.PlayistResponse;
import android.project.spotitop.data.api.serialization.Track;

import io.reactivex.Single;

public class TopSongsDisplayRemoteDataSource {

    private TopSongsDisplayService topSongsDisplayService;

    public TopSongsDisplayRemoteDataSource(TopSongsDisplayService topSongsDisplayService) {
        this.topSongsDisplayService = topSongsDisplayService;
    }

    //id of the playist
    public Single<PlayistResponse> getDailyTopPlayistResponse() {
        return topSongsDisplayService.getTopTracks(SpotiTopApplication.ID_TOP_PLAYIST, SpotiTopApplication.API_KEY);
    }

    public Single<Track> getTrackDetails(String id){
        return topSongsDisplayService.getTrackDetails(id, SpotiTopApplication.API_KEY);
    }
}
