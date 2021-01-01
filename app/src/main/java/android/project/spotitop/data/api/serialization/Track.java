package android.project.spotitop.data.api.serialization;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Track object which contains informations about a track
 */
public class Track {
    // Spotify id of the track
    @SerializedName("id")
    private String id;

    // Album object of the track
    @SerializedName("album")
    Album album;

    // List of artists of the track
    @SerializedName("artists")
    List<Artist> artists;

    // Duration in milliseconds of the track
    @SerializedName("duration_ms")
    Integer trackDurationInMs;

    // Name of the track
    @SerializedName("name")
    String trackName;

    public String getArtistsToString() {
        String res = "";
        for (Artist artist : artists) {
            res += artist.getArtistName() + " - ";
        }
        return res.substring(0, res.length()-3);
    }

    public String getReadableTrackDurationFromMs() {
        int durationInSec = trackDurationInMs/1000;
        int nbOfMinutes = durationInSec/60;
        int nbOfSec = durationInSec%60;
        return Integer.toString(nbOfMinutes) + ":" + Integer.toString(nbOfSec);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getTrackDurationInMs() {
        return trackDurationInMs;
    }

    public void setTrackDurationInMs(Integer trackDurationInMs) {
        this.trackDurationInMs = trackDurationInMs;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
}


