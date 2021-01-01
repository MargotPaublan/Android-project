package android.project.spotitop.data.api.serialization;

import com.google.gson.annotations.SerializedName;

/**
 * Object which contains one track informations
 */
public class Item {
    // Track object which contains informations about a track
    @SerializedName("track")
    Track track;

    public Track getTrack() {
        return track;
    }
}
