package android.project.spotitop.presentation.topsongsdisplay.research.adapter;

import java.io.Serializable;

public class TrackViewItem implements Serializable {
    private String trackId;
    private String trackName;
    private String trackAlbum;
    private String trackArtists;
    private String trackImageUrl;
    private String trackRank;
    private String trackDuration;
    private String trackReleaseDate;


    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
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

    public String getTrackImageUrl() {
        return trackImageUrl;
    }

    public void setTrackImageUrl(String trackImageUrl) {
        this.trackImageUrl = trackImageUrl;
    }

    public String getTrackRank() {
        return trackRank;
    }

    public void setTrackRank(String trackRank) {
        this.trackRank = trackRank;
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
}
