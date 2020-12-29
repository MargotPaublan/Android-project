package android.project.spotitop.data.api.serialization;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {
    @SerializedName("track")
    Track track;

    public Track getTrack() {
        return track;
    }
}
