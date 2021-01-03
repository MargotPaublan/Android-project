package android.project.spotitop.presentation.topsongsdisplay.favorite.adapter;

public interface FavoriteTrackActionInterface {
    void removeTrackFromFavorites(String trackId);
    void onFavoriteButton(String trackId, boolean isFavorite);
}
