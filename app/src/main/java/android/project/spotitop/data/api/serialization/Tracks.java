package android.project.spotitop.data.api.serialization;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tracks {
    // A list of tracks objects
    @SerializedName("items")
    List<Item> items;

    public List<Item> getItems() {
        return this.items;
    }
}
