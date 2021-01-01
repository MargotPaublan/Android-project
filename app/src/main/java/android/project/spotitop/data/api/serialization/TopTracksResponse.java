package android.project.spotitop.data.api.serialization;

import com.google.gson.annotations.SerializedName;

/**
 * The spotify api response with last daily top tracks
 */
public class TopTracksResponse {
    // Object that contains the last daily top tracks, among other (useless) things
    @SerializedName("tracks")
    ListOfTracks listOfTracks;

    public ListOfTracks getListOfTracks() {
        return listOfTracks;
    }

}
