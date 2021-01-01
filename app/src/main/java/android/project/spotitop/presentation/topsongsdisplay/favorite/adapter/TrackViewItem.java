package android.project.spotitop.presentation.topsongsdisplay.favorite.adapter;

import java.util.List;

public class TrackViewItem {
    private String trackId;
    private String iconUrl;
    private String trackTitle;
    private String trackArtists;
    private String trackAlbum;
    private int trackDuration;
    private String rank;


    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
    }

    public String getTrackArtists() {
        return trackArtists;
    }

    public void setTrackArtists(String trackArtists) {
        this.trackArtists = trackArtists;
    }

    public String getTrackAlbum() {
        return trackAlbum;
    }

    public void setTrackAlbum(String trackAlbum) {
        this.trackAlbum = trackAlbum;
    }

    public int getTrackDuration() {
        return trackDuration;
    }

    public void setTrackDuration(int trackDuration) {
        this.trackDuration = trackDuration;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}