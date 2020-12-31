package android.project.spotitop.data.api.serialization;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Album {
    // List of album images
    @SerializedName("images")
    List<AlbumImage> albumImages;

    // Album name
    @SerializedName("name")
    String albumName;

    // Album releaseDate
    @SerializedName("release_date")
    String releaseDate;

    public String getAlbumName() {
        return albumName;
    }

    public List<AlbumImage> getAlbumImages() {
        return albumImages;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
