package android.project.spotitop.presentation.viewmodel;

import android.os.Build;
import android.project.spotitop.data.api.serialization.AuthorizationResponse;
import android.project.spotitop.data.api.serialization.Item;
import android.project.spotitop.data.api.serialization.TopTracksResponse;
import android.project.spotitop.data.api.serialization.Track;
import android.project.spotitop.data.database.TrackEntity;
import android.project.spotitop.data.repository.topsongsdisplay.TopSongsDisplayRepository;
import android.project.spotitop.data.repository.topsongsdisplay.remote.AuthorizationToken;
import android.project.spotitop.presentation.topsongsdisplay.favorite.mapper.TrackEntityToTrackFavoriteViewItemMapper;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackViewItem;
import android.project.spotitop.presentation.topsongsdisplay.research.mapper.TrackToTrackViewItemMapper;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;



public class DailyTopTracksViewModel extends ViewModel {
    private TopSongsDisplayRepository topSongsDisplayRepository;
    private CompositeDisposable compositeDisposable;
    private AuthorizationToken authorizationToken;
    private TrackToTrackViewItemMapper trackToTrackViewItemMapper;

    public DailyTopTracksViewModel(TopSongsDisplayRepository topSongsDisplayRepository) {
        this.topSongsDisplayRepository = topSongsDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.trackToTrackViewItemMapper = new TrackToTrackViewItemMapper();
        this.authorizationToken = AuthorizationToken.getInstance();
    }

    private MutableLiveData<List<TrackViewItem>> tracks = new MutableLiveData<List<TrackViewItem>>();

    public MutableLiveData<List<TrackViewItem>> getTracks() {
        return tracks;
    }
    List<String> favoriteTracksIds;





    public  List<String> getFavoriteTracks() {
        // If favoriteBooks is empty
        if (favoriteTracksIds == null) {
            favoriteTracksIds = new ArrayList<String>();

            compositeDisposable.add(topSongsDisplayRepository.getSavedTracks()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new ResourceSubscriber<List<TrackEntity>>() {
                        @Override
                        public void onNext(List<TrackEntity> trackEntities) {
                            List<String> trackListResponse = new ArrayList<>();
                            for (TrackEntity trackEntity : trackEntities) {
                                favoriteTracksIds.add(trackEntity.getTrackId());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            // handle the error case
                            System.out.println(e.toString());
                        }

                        @Override
                        public void onComplete() {
                            // Do nothing
                        }

                    }));

        }
        return favoriteTracksIds;
    }

    public void getAuthorizationToContactSpotifyAPI() {
        compositeDisposable.clear();
        compositeDisposable.add(topSongsDisplayRepository.getAuthorizationToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<AuthorizationResponse>() {

                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onSuccess(AuthorizationResponse authorizationResponse) {

                        authorizationToken.setExpiresAt(authorizationResponse.getExpiresInSec());
                        authorizationToken.setTokenBearer(authorizationResponse.getAccessToken());
                        authorizationToken.setTokenType(authorizationResponse.getTokenType());
                        Log.i("token1", "ok" + authorizationToken.getTokenBearer());
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        System.out.println(e.toString());
                    }
                }));
    }

    //TODO : handle loader
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void searchTopPlayist() {
        compositeDisposable.clear();

        // Get favorites tracks from database
        List<String> favoriteTracks = getFavoriteTracks();

        compositeDisposable.add(topSongsDisplayRepository.getDailyTopPlayistResponse(authorizationToken.getTokenAuthorization())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<TopTracksResponse>() {

                    @Override
                    public void onSuccess(TopTracksResponse topTracksResponse) {
                        List<Track> trackListResponse = new ArrayList<>();

                        // 
                        for (String id : favoriteTracks) {

                            for (Item item : topTracksResponse.getListOfTracks().getItems()) {
                                if (id.equals(item.getTrack().getId())) {
                                    item.getTrack().setFavorite(true);
                                }
                                trackListResponse.add(item.getTrack());
                            }

                        }



                        tracks.setValue(trackToTrackViewItemMapper.map(trackListResponse));
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        System.out.println(e.toString());
                    }
                }));
    }

}
