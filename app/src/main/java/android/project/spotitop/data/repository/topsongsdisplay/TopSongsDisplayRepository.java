package android.project.spotitop.data.repository.topsongsdisplay;

import android.project.spotitop.data.api.serialization.PlayistResponse;
import android.project.spotitop.data.database.TrackEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface TopSongsDisplayRepository {
    Single<PlayistResponse> getDailyTopPlayistResponse();

    Flowable<List<TrackEntity>> getSavedTracks();

    Completable saveTrack(String id);

    Completable removeTrackFromDB(String id);
}
