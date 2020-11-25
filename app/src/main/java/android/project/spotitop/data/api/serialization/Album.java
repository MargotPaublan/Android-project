package android.project.spotitop.data.api.serialization;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Album {
    // List of album images
    @SerializedName("images")
    List<AlbumImages> AlbumImageUrl;

    // Album name
    @SerializedName("name")
    String albumName;
}
