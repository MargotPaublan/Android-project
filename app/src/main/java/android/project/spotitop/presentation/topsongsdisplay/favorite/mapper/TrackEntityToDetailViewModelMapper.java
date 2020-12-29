package android.project.spotitop.presentation.topsongsdisplay.favorite.mapper;

import android.project.spotitop.data.database.TrackEntity;
import android.project.spotitop.presentation.topsongsdisplay.favorite.adapter.TrackDetailViewItem;

import java.util.ArrayList;
import java.util.List;

public class TrackEntityToDetailViewModelMapper {
    // Map a TrackEntity to a TrackDetailViewItem
    public TrackDetailViewItem map(TrackEntity trackEntity){
        // Create TrackDetailViewItem
        TrackDetailViewItem trackDetailViewItem = new TrackDetailViewItem();

        // Set TrackDetailViewItem infos from TrackEntity parameters
        trackDetailViewItem.setTrackId(trackEntity.getId());
        trackDetailViewItem.setTrackTitle(trackEntity.getTitle());
        //trackDetailViewItem.setTrackArtists(trackEntity.getArtists());
        trackDetailViewItem.setTrackAlbum(trackEntity.getAlbum());
        trackDetailViewItem.setTrackDuration(trackEntity.getDuration());
        //trackDetailViewItem.setIconUrl(trackEntity.getIconUrl());
        return trackDetailViewItem;
    }

    // Map a list of BookEntities to a list of BookDetailViewItems
    public List<TrackDetailViewItem> map(List<TrackEntity> trackEntitiesList) {
        ArrayList<TrackDetailViewItem> trackDetailViewItemArrayList = new ArrayList<>();

        for(TrackEntity trackEntity : trackEntitiesList){
            trackDetailViewItemArrayList.add(map(trackEntity));
        }
        return trackDetailViewItemArrayList;
    }

}
