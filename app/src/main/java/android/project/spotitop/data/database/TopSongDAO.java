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

    @Query("SELECT * FROM topSongs")
    Flowable<List<TrackEntity>> getLastDailyTop();

    @Insert
    Completable addSong(TrackEntity trackEntity);

    @Query("DELETE FROM topSongs WHERE id = :id")
    Completable deleteSong(String id);



    @Query("SELECT id from topSongs")
    Single<List<String>> getLastDailyTopIDList();

}