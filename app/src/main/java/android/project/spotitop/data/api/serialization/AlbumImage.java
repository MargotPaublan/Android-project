package android.project.spotitop.data.api.serialization;

import com.google.gson.annotations.SerializedName;

/**
 * Represents an image of an album
 */
public class AlbumImage {
    // URL of the album image
    @SerializedName("url")
    String imageURL;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
