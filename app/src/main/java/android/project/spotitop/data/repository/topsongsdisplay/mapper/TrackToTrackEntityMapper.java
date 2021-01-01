package android.project.spotitop.data.repository.topsongsdisplay.mapper;

import android.project.spotitop.data.api.serialization.Track;
import android.project.spotitop.data.database.TrackEntity;


/**
 * Maps a Track object (received from Spotify API) to a Track entity object to store it in database
 */
public class TrackToTrackEntityMapper {
    public TrackEntity map(Track track) {
        // Create a track entity object
        TrackEntity trackEntity = new TrackEntity();

        // Set the track entity infos from track object infos
        trackEntity.setTrackId(track.getId());
        trackEntity.setTrackName(track.getTrackName());
        trackEntity.setTrackAlbum(track.getAlbum().getAlbumName());
        trackEntity.setTrackArtists(track.getArtistsToString());
        trackEntity.setTrackIconUrl(track.getAlbum().getAlbumImages().get(0).getImageURL());
        trackEntity.setTrackDuration(track.getReadableTrackDurationFromMs());
        trackEntity.setTrackReleaseDate(track.getAlbum().getReleaseDate());

        return trackEntity;
    }
}

