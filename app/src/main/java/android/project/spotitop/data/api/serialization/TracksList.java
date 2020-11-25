package android.project.spotitop.data.api.serialization;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TracksList {
    // A list of tracks objects
    @SerializedName("tracks")
    List<Track> tracks;
}
