package android.project.spotitop.presentation.topsongsdisplay.research.adapter;

import java.io.Serializable;
import java.util.List;

public class TrackViewItem implements Serializable {
    private String trackId;
    private String trackName;
    private List<String> artists;
    private String albumName;
    private String trackDurationMs;
    private String trackDurationReadable;
    private String albumReleaseDate;
    private List<String> albumImagesUrls;
    private String rank;

    public String getTrackDurationMs() {
        return trackDurationMs;
    }

    public void setTrackDurationMs(String trackDurationMs) {
        this.trackDurationMs = trackDurationMs;
    }



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

    public List<String> getArtists() {
        return artists;
    }

    public String getArtistsToString() {
        String res = "";
        for (String artist : artists) {
            res += artist + " - ";
        }
        return res.substring(0, res.length()-3);
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public List<String> getAlbumImagesUrls() {
        return albumImagesUrls;
    }

    public String getFirstAlbumImageUrl() {
        return albumImagesUrls.get(0);
    }

    public String getSecondAlbumImageUrl() {
        return albumImagesUrls.get(1);
    }

    public void setAlbumImagesUrls(List<String> albumImgagesUrls) {
        this.albumImagesUrls = albumImgagesUrls;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAlbumReleaseDate() {
        return albumReleaseDate;
    }

    public void setAlbumReleaseDate(String albumReleaseDate) {
        this.albumReleaseDate = albumReleaseDate;
    }

    public String getTrackDurationReadable() {
        return trackDurationReadable;
    }

    public void setTrackDurationReadable(String trackDurationReadable) {
        this.trackDurationReadable = trackDurationReadable;
    }
}
