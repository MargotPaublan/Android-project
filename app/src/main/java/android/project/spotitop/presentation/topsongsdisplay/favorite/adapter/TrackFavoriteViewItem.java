package android.project.spotitop.presentation.topsongsdisplay.favorite.adapter;

public class TrackFavoriteViewItem {
    private String trackId;
    private String trackIconUrl;
    private String trackName;
    private String trackArtists;
    private String trackAlbum;
    private String trackDuration;
    private String trackReleaseDate;
    private boolean isFavorite = false;


    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTrackIconUrl() {
        return trackIconUrl;
    }

    public void setTrackIconUrl(String trackIconUrl) {
        this.trackIconUrl = trackIconUrl;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
