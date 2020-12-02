package android.project.spotitop.data.api.serialization;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Track {
    private String id;
    // Album object of the track
    @SerializedName("album")
    Album album;


    // List of authors of the tracks
    @SerializedName("artists")
    List<Artist> artists;

    // Duration in milliseconds of the track
    @SerializedName("duration_ms")
    Integer durationMs;

    // Name of the track
    @SerializedName("name")
    String trackName;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Integer getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Integer durationMs) {
        this.durationMs = durationMs;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


