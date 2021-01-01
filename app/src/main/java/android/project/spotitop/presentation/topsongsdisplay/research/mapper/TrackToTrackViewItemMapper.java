package android.project.spotitop.presentation.topsongsdisplay.research.mapper;

import android.project.spotitop.data.api.serialization.Track;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackViewItem;

import java.util.ArrayList;
import java.util.List;

public class TrackToTrackViewItemMapper {
    private TrackViewItem map(Track track, String rank) {
        //Create new track view item
        TrackViewItem trackViewItem = new TrackViewItem();

        // Map the new track view item fields from the track object
        trackViewItem.setTrackId(track.getId());
        trackViewItem.setTrackName(track.getTrackName());
        trackViewItem.setTrackAlbum(track.getAlbum().getAlbumName());
        trackViewItem.setTrackArtists(track.getArtistsToString());
        trackViewItem.setTrackRank(rank);
        trackViewItem.setTrackDuration(track.getReadableTrackDurationFromMs());
        trackViewItem.setTrackReleaseDate(track.getAlbum().getReleaseDate());
        trackViewItem.setTrackImageUrl(track.getAlbum().getAlbumImages().get(0).getImageURL());


        /*

        bookViewItem.setFavorite(book.isFavorite());

        */

        return trackViewItem;
    }



    public List<TrackViewItem> map(List<Track> tracksList) {
        List<TrackViewItem> trackViewItemList = new ArrayList<>();
        Integer index = 1;
        for (Track track : tracksList) {
            trackViewItemList.add(map(track, index.toString()));
            index++;
        }
        return trackViewItemList;
    }
}
