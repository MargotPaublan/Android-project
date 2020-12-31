package android.project.spotitop.presentation.topsongsdisplay.research.mapper;

import android.project.spotitop.data.api.serialization.AlbumImage;
import android.project.spotitop.data.api.serialization.Artist;
import android.project.spotitop.data.api.serialization.Track;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackViewItem;

import java.util.ArrayList;
import java.util.List;

public class TrackToTrackViewItemMapper {
    private TrackViewItem map(Track track, String rank) {
        //Create new track view item
        TrackViewItem trackViewItem = new TrackViewItem();

        // Map the new track view item fields from the track object
        //// track id
        trackViewItem.setTrackId(track.getId());

        //// track name
        trackViewItem.setTrackName(track.getTrackName());

        //// track album name
        trackViewItem.setAlbumName(track.getAlbum().getAlbumName());

        //// track artists
        List<String> artistsList = new ArrayList<String>();
        for (Artist artist : track.getArtists()) {
            artistsList.add(artist.getArtistName());
        }
        trackViewItem.setArtists(artistsList);

        //// track duration
        //trackViewItem.setTrackDurationMs(Integer.toString(track.getDurationMs()));

        //// track readable duration
        trackViewItem.setTrackDurationReadable(this.readableTrackDurationFromMs(track.getDurationMs()));

        //// track readable duration
        trackViewItem.setAlbumReleaseDate(track.getAlbum().getReleaseDate());


        //// track's album images
        List<String> albumImagesUrls = new ArrayList<String>();
        for (AlbumImage albumImage : track.getAlbum().getAlbumImages()) {
            albumImagesUrls.add(albumImage.getImageURL());
        }
        trackViewItem.setAlbumImagesUrls(albumImagesUrls);

        //// track rank
        trackViewItem.setRank(rank);

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

    public String readableTrackDurationFromMs(int durationMs) {
        int durationInSec = durationMs/1000;
        int nbOfMinutes = durationInSec/60;
        int nbOfSec = durationInSec%60;
        return Integer.toString(nbOfMinutes) + ":" + Integer.toString(nbOfSec);
    }
}
