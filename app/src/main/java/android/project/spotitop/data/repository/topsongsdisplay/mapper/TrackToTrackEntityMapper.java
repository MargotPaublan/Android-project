package android.project.spotitop.data.repository.topsongsdisplay.mapper;

import android.project.spotitop.data.api.serialization.Track;
import android.project.spotitop.data.database.TrackEntity;

import io.reactivex.Single;

public class TrackToTrackEntityMapper {
    public Single<TrackEntity> map(Single<Track> track) {
    }
}
