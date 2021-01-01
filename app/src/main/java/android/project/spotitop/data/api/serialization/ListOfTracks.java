package android.project.spotitop.data.api.serialization;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Object that contains the last daily top tracks
 */
public class ListOfTracks {
    // A list of objects, each ones containing one track informations
    @SerializedName("items")
    List<Item> items;

    public List<Item> getItems() {
        return this.items;
    }
}
