package android.project.spotitop.data.repository.topsongsdisplay;

import android.project.spotitop.data.api.serialization.PlayistResponse;
import android.project.spotitop.data.api.serialization.Track;
import android.project.spotitop.data.database.TrackEntity;
import android.project.spotitop.data.repository.topsongsdisplay.local.TopSongsDisplayLocalDataSource;
import android.project.spotitop.data.repository.topsongsdisplay.mapper.TrackToTrackEntityMapper;
import android.project.spotitop.data.repository.topsongsdisplay.remote.TopSongsDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class TopSongsDisplayDataRepository implements TopSongsDisplayRepository {
    private TopSongsDisplayRemoteDataSource topSongsDisplayRemoteDataSource;
    private TopSongsDisplayLocalDataSource topSongsDisplayLocalDataSource;
    private TrackToTrackEntityMapper trackToTrackEntityMapper;


    public TopSongsDisplayDataRepository(TopSongsDisplayRemoteDataSource topSongsDisplayRemoteDataSource,
                                         TopSongsDisplayLocalDataSource topSongsDisplayLocalDataSource,
                                         TrackToTrackEntityMapper trackToTrackEntityMapper) {
        this.topSongsDisplayRemoteDataSource = topSongsDisplayRemoteDataSource;
        this.topSongsDisplayLocalDataSource = topSongsDisplayLocalDataSource;
        this.trackToTrackEntityMapper = trackToTrackEntityMapper;
    }


    @Override
    public Single<PlayistResponse> getDailyTopPlayistResponse() {
        return topSongsDisplayRemoteDataSource.getDailyTopPlayistResponse();
    }

    @Override
    public Flowable<List<TrackEntity>> getSavedTracks() {
        return topSongsDisplayLocalDataSource.getSavedTracks();
    }

    @Override
    public Completable saveTrack(String id) {
        Single<Track> track =  topSongsDisplayRemoteDataSource.getTrackDetails(id);
        Single<TrackEntity> trackEntity = track.map(new Function<Track, TrackEntity>() {
            @Override
            public TrackEntity apply(Track track) throws Exception {
                return trackToTrackEntityMapper.map(track);
            }
        });



        Completable trackEntityResult = trackEntity.flatMapCompletable(new Function<TrackEntity, CompletableSource>() {
            @Override
            public CompletableSource apply(TrackEntity trackEntity) throws Exception {
                return topSongsDisplayLocalDataSource.saveTrack(trackEntity);
            }
        });
        return trackEntityResult;
    }



    @Override
    public Completable removeTrackFromDB(String id) {
        return topSongsDisplayLocalDataSource.deleteTrack(id);
    }
}
