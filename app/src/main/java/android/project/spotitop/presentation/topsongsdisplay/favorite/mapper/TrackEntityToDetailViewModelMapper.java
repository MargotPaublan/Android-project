package android.project.spotitop.presentation.topsongsdisplay.favorite.mapper;

import android.project.spotitop.data.database.TrackEntity;
import android.project.spotitop.presentation.topsongsdisplay.favorite.adapter.TrackViewItem;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

public class TrackEntityToDetailViewModelMapper {
    // Map a TrackEntity to a TrackDetailViewItem
    public TrackViewItem map(TrackEntity trackEntity){
        // Create TrackDetailViewItem
        TrackViewItem trackViewItem = new TrackViewItem();

        // Set TrackDetailViewItem infos from TrackEntity parameters

        trackViewItem.setTrackId(trackEntity.getId());

        trackViewItem.setTrackTitle(trackEntity.getTitle());

        //trackViewItem.setArtists(trackEntity.getArtists());
        Log.i("tracktitle2", trackEntity.getAlbum());
        trackViewItem.setTrackAlbum(trackEntity.getAlbum());
        trackViewItem.setRank(trackEntity.getAlbum());
        //trackViewItem.setTrackDuration(trackEntity.getDuration());
        trackViewItem.setIconUrl(trackEntity.getIconUrl());
        return trackViewItem;
    }

    // Map a list of BookEntities to a list of BookDetailViewItems
    public List<TrackViewItem> map(List<TrackEntity> trackEntitiesList) {
        ArrayList<TrackViewItem> trackViewItemArrayList = new ArrayList<>();

        for(TrackEntity trackEntity : trackEntitiesList){
            trackViewItemArrayList.add(map(trackEntity));
        }
        return trackViewItemArrayList;
    }

}
