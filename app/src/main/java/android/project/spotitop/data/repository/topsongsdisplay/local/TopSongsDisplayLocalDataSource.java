package android.project.spotitop.data.repository.topsongsdisplay.local;

import android.project.spotitop.data.database.TopSongDatabase;
import android.project.spotitop.data.database.TrackEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class TopSongsDisplayLocalDataSource {
    private TopSongDatabase topSongDatabase;

    public TopSongsDisplayLocalDataSource(TopSongDatabase topSongDatabase){
        this.topSongDatabase = topSongDatabase;
    }


    public TopSongDatabase getTopSongDatabase() {
        return topSongDatabase;
    }

    public Flowable<List<TrackEntity>> getSavedTracks() {
        return topSongDatabase.topSongsDAO().getLastDailyTop();
    }

    public Completable deleteTrack(String id) {
        return topSongDatabase.topSongsDAO().deleteTrack(id);
    }

    public Completable saveTrack(TrackEntity trackEntity) {
        return topSongDatabase.topSongsDAO().addTrack(trackEntity);
    }
}
