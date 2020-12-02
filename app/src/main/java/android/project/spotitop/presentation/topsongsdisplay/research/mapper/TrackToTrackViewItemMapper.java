package android.project.spotitop.presentation.topsongsdisplay.research.mapper;

import android.project.spotitop.data.api.serialization.Artist;
import android.project.spotitop.data.api.serialization.Track;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackViewItem;

import java.util.ArrayList;
import java.util.List;

public class TrackToTrackViewItemMapper {
    private TrackViewItem map(Track track) {
        //Create new track view item
        TrackViewItem trackViewItem = new TrackViewItem();

        // Map the new track view item fields from the track object
        //// track id
        trackViewItem.setTrackId(track.getId());

        //// track name
        trackViewItem.setTrackName(track.getTrackName());

        //// track artists
        List<String> artistsList = new ArrayList<String>();
        for (Artist artist : track.getArtists()) {
            artistsList.add(artist.getArtistName());
        }

        //// track duration
        trackViewItem.setTrackDuration(Integer.toString(track.getDurationMs()));

        //todo : image mapping
        /*
        if (book.getVolumeInfo().getImageLinks() != null) {
            bookViewItem.setIconUrl(book.getVolumeInfo().getImageLinks().getThumbnail());
        }

        bookViewItem.setFavorite(book.isFavorite());

        if (book.getVolumeInfo().getAuthorList() == null) {
            bookViewItem.setBookAuthors("vide");
        } else {
            bookViewItem.setBookAuthors(TextUtils.join(", ", book.getVolumeInfo().getAuthorList()));
        }*/

        return trackViewItem;
    }

    public List<TrackViewItem> map(List<Track> tracksList) {
        List<TrackViewItem> trackViewItemList = new ArrayList<>();
        for (Track track : tracksList) {
            trackViewItemList.add(map(track));
        }
        return trackViewItemList;
    }
}
