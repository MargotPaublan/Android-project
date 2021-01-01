package android.project.spotitop.data.api.serialization;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Album object of the track
 */
public class Album {
    // List of the album images
    @SerializedName("images")
    List<AlbumImage> albumImages;

    // Album name
    @SerializedName("name")
    String albumName;

    // Album releaseDate
    @SerializedName("release_date")
    String releaseDate;


    public List<AlbumImage> getAlbumImages() {
        return albumImages;
    }

    public void setAlbumImages(List<AlbumImage> albumImages) {
        this.albumImages = albumImages;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
