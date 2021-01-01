package android.project.spotitop.data.repository.topsongsdisplay.mapper;

import android.project.spotitop.data.api.serialization.Album;
import android.project.spotitop.data.api.serialization.Artist;
import android.project.spotitop.data.api.serialization.Track;
import android.project.spotitop.data.database.TrackEntity;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.reactivex.Single;

public class TrackToTrackEntityMapper {
    public TrackEntity map(Track track) {
        // Create a bookEntity object
        TrackEntity trackEntity = new TrackEntity();

        // Set bookEntity infos from book object infos
        trackEntity.setId(track.getId());
        trackEntity.setTitle(track.getTrackName());
        Log.i("tracktitle3", track.getAlbum().getAlbumName());
        trackEntity.setAlbum(track.getAlbum().getAlbumName());
        trackEntity.setArtists(track.getArtists().toString());
        trackEntity.setIconUrl(track.getAlbum().getAlbumImages().get(0).getImageURL());

        return trackEntity;
    }
}

