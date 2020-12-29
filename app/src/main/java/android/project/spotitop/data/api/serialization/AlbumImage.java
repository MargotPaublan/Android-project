package android.project.spotitop.data.api.serialization;

import com.google.gson.annotations.SerializedName;

public class AlbumImage {
    // URL of the album image
    @SerializedName("url")
    String imageURL;

    public String getImageURL() {
        return imageURL;
    }
}
