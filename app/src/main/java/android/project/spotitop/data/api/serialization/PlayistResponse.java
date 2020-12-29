package android.project.spotitop.data.api.serialization;

import com.google.gson.annotations.SerializedName;

public class PlayistResponse {
    // Title of the playist
    @SerializedName("name")
    String title;

    // Playist description
    @SerializedName("description")
    String description;

    // Object that contains the list of tracks, among other (useless) things
    @SerializedName("tracks")
    Tracks tracks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    int totalItems;


    public int getTotalItems() {
        return totalItems;
    }
}
