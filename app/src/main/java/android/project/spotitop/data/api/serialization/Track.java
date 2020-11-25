package android.project.spotitop.data.api.serialization;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Track {
    // Album object of the track
    @SerializedName("album")
    Album album;


    // List of authors of the tracks
    @SerializedName("artists")
    List<Artist> artists;

    // Duration in milliseconds of the track
    @SerializedName("duration_ms")
    Integer durationMs;

    // Name of the track
    @SerializedName("name")
    String trackName;

}


