package android.project.spotitop.data.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity of the topSongTable, with some informations about a track
 */
@Entity(tableName = "topSongsTable")
public class TrackEntity {
    @NonNull
    @PrimaryKey
    public String trackId;
    public String trackName;
    public String trackAlbum;
    public String trackArtists;
    public String trackDuration;
    public String trackReleaseDate;
    public String trackIconUrl;


    @NonNull
    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(@NonNull String trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackAlbum() {
        return trackAlbum;
    }

    public void setTrackAlbum(String trackAlbum) {
        this.trackAlbum = trackAlbum;
    }

    public String getTrackArtists() {
        return trackArtists;
    }

    public void setTrackArtists(String trackArtists) {
        this.trackArtists = trackArtists;
    }

    public String getTrackDuration() {
        return trackDuration;
    }

    public void setTrackDuration(String trackDuration) {
        this.trackDuration = trackDuration;
    }

    public String getTrackReleaseDate() {
        return trackReleaseDate;
    }

    public void setTrackReleaseDate(String trackReleaseDate) {
        this.trackReleaseDate = trackReleaseDate;
    }

    public String getTrackIconUrl() {
        return trackIconUrl;
    }

    public void setTrackIconUrl(String trackIconUrl) {
        this.trackIconUrl = trackIconUrl;
    }
}