package android.project.spotitop.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;


@Dao
public interface FavoriteTopTracksDAO {
    /**
     * Get saved favorite top tracks
     * @return favorite top tracks from database
     */
    @Query("SELECT * FROM topSongsTable")
    Flowable<List<TrackEntity>> getFavoriteTracks();

    /**
     * Add a track to favorites
     * @param trackEntity
     */
    @Insert
    Completable saveFavoriteTrackIntoDatabase(TrackEntity trackEntity);

    /**
     * Removes a track from favorites
     * @param trackId the id of the track to remove
     */
    @Query("DELETE FROM topSongsTable WHERE trackId = :trackId")
    Completable deleteFavoriteTrackFromDatabase(String trackId);
}