package android.project.spotitop.data.database;

import androidx.room.Dao;

import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface TopSongDAO {

    @Query("SELECT * FROM topSongsTable")
    Flowable<List<TrackEntity>> getLastDailyTop();

    @Insert
    Completable addTrack(TrackEntity trackEntity);

    @Query("DELETE FROM topSongsTable WHERE id = :id")
    Completable deleteTrack(String id);



    @Query("SELECT id from topSongsTable")
    Single<List<String>> getLastDailyTopIDList();

}