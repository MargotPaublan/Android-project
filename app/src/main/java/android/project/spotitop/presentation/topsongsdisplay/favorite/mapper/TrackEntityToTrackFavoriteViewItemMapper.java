package android.project.spotitop.presentation.topsongsdisplay.favorite.mapper;

import android.project.spotitop.data.database.TrackEntity;
import android.project.spotitop.presentation.topsongsdisplay.favorite.adapter.TrackFavoriteViewItem;
import java.util.ArrayList;
import java.util.List;

public class TrackEntityToTrackFavoriteViewItemMapper {
    // Map a TrackEntity to a TrackDetailViewItem
    public TrackFavoriteViewItem map(TrackEntity trackEntity){
        // Create TrackDetailViewItem
        TrackFavoriteViewItem trackFavoriteViewItem = new TrackFavoriteViewItem();

        // Set TrackDetailViewItem infos from TrackEntity parameters
        trackFavoriteViewItem.setTrackId(trackEntity.getTrackId());
        trackFavoriteViewItem.setTrackName(trackEntity.getTrackName());
        trackFavoriteViewItem.setTrackAlbum(trackEntity.getTrackAlbum());
        trackFavoriteViewItem.setTrackArtists(trackEntity.getTrackArtists());
        trackFavoriteViewItem.setTrackDuration(trackEntity.getTrackDuration());
        trackFavoriteViewItem.setTrackReleaseDate(trackEntity.getTrackReleaseDate());
        trackFavoriteViewItem.setTrackIconUrl(trackEntity.getTrackIconUrl());

        return trackFavoriteViewItem;
    }

    // Map a list of TrackEntities to a list of TrackDetailViewItems
    public List<TrackFavoriteViewItem> map(List<TrackEntity> trackEntitiesList) {
        ArrayList<TrackFavoriteViewItem> trackFavoriteViewItemArrayList = new ArrayList<>();

        for(TrackEntity trackEntity : trackEntitiesList){
            trackFavoriteViewItemArrayList.add(map(trackEntity));
        }
        return trackFavoriteViewItemArrayList;
    }

}
