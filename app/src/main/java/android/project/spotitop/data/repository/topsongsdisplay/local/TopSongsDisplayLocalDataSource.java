package android.project.spotitop.data.repository.topsongsdisplay.local;

import android.project.spotitop.data.database.FavoriteTopTracksDatabase;
import android.project.spotitop.data.database.TrackEntity;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Links the repository to the local database (which contains informations about user favorite tracks)
 */
public class TopSongsDisplayLocalDataSource {
    // Favorite tracks database
    private FavoriteTopTracksDatabase favoriteTopTracksDatabase;

    public TopSongsDisplayLocalDataSource(FavoriteTopTracksDatabase favoriteTopTracksDatabase){
        this.favoriteTopTracksDatabase = favoriteTopTracksDatabase;
    }


    /**
     * Get all the favorite tracks stored in database
     * @return the user favorite tracks
     */
    public Flowable<List<TrackEntity>> getFavoriteSavedTracks() {
        return favoriteTopTracksDatabase.topSongsDAO().getFavoriteTracks();
    }

    /**
     * Remove the track with the id "trackId" from the database
     * @param trackId : the id of the track to remove
     */
    public Completable deleteFavoriteTrackFromDatabase(String trackId) {
        return favoriteTopTracksDatabase.topSongsDAO().deleteFavoriteTrackFromDatabase(trackId);
    }

    /**
     * Save the track entity into the "favoriteTracks" database
     * @param trackEntity : the track entity to store into the database
     */
    public Completable saveFavoriteTrackIntoDatabase(TrackEntity trackEntity) {
        return favoriteTopTracksDatabase.topSongsDAO().saveFavoriteTrackIntoDatabase(trackEntity);
    }

}
