package android.project.spotitop.data.repository.topsongsdisplay.mapper;

import android.project.spotitop.data.api.serialization.Album;
import android.project.spotitop.data.api.serialization.Artist;
import android.project.spotitop.data.api.serialization.Track;
import android.project.spotitop.data.database.TrackEntity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.reactivex.Single;

public class TrackToTrackEntityMapper {
    public TrackEntity map(Track track) {
        // Create a bookEntity object
        TrackEntity trackEntity = new TrackEntity();

        // Set bookEntity infos from book object infos
        trackEntity.setTitle(track.getTrackName());
        trackEntity.setAlbum(track.getAlbum().toString());
        trackEntity.setDuration(track.getDurationMs());

        /*if (track.getArtists() == null) {
            trackEntity.setArtists("Empty artists list");
        } else {
            trackEntity.setArtists("Empty artists list");
        }*/

        return trackEntity;
    }
}

