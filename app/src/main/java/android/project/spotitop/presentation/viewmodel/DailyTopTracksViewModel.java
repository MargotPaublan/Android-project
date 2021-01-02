package android.project.spotitop.presentation.viewmodel;

import android.os.Build;
import android.project.spotitop.data.api.serialization.AuthorizationResponse;
import android.project.spotitop.data.api.serialization.Item;
import android.project.spotitop.data.api.serialization.TopTracksResponse;
import android.project.spotitop.data.api.serialization.Track;
import android.project.spotitop.data.database.TrackEntity;
import android.project.spotitop.data.repository.topsongsdisplay.TopSongsDisplayRepository;
import android.project.spotitop.data.repository.topsongsdisplay.remote.AuthorizationToken;
import android.project.spotitop.presentation.topsongsdisplay.favorite.adapter.TrackFavoriteViewItem;
import android.project.spotitop.presentation.topsongsdisplay.favorite.mapper.TrackEntityToTrackFavoriteViewItemMapper;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackViewItem;
import android.project.spotitop.presentation.topsongsdisplay.research.mapper.TrackToTrackViewItemMapper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class DailyTopTracksViewModel extends ViewModel {
    private TopSongsDisplayRepository topSongsDisplayRepository;
    private CompositeDisposable compositeDisposable;
    private TrackToTrackViewItemMapper trackToTrackViewItemMapper;
    private AuthorizationToken authorizationToken;
    private TrackEntityToTrackFavoriteViewItemMapper trackEntityToTrackFavoriteViewItemMapper;

    public DailyTopTracksViewModel(TopSongsDisplayRepository topSongsDisplayRepository) {
        this.topSongsDisplayRepository = topSongsDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.trackToTrackViewItemMapper = new TrackToTrackViewItemMapper();
        this.authorizationToken = AuthorizationToken.getInstance();
        this.trackEntityToTrackFavoriteViewItemMapper = new TrackEntityToTrackFavoriteViewItemMapper();
    }

    private MutableLiveData<List<TrackViewItem>> tracks = new MutableLiveData<List<TrackViewItem>>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();
    final MutableLiveData<Event<String>> trackAddedEvent = new MutableLiveData<>();

    public MutableLiveData<List<TrackViewItem>> getTracks() {
        return tracks;
    }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }
    List<String> favoriteTracksIds;


    public MutableLiveData<Event<String>> getTrackAddedEvent() {
        return trackAddedEvent;
    }

    public void addTrackToFavorites(final String trackId){
        trackAddedEvent.setValue(new Event<>(trackId));
    }



    public  List<String> getFavoriteTracks() {
        isDataLoading.setValue(true);

        // If favoriteBooks is empty
        if (favoriteTracksIds == null) {
            favoriteTracksIds = new ArrayList<String>();

            compositeDisposable.add(topSongsDisplayRepository.getSavedTracks()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new ResourceSubscriber<List<TrackEntity>>() {
                        @Override
                        public void onNext(List<TrackEntity> trackEntities) {
                            isDataLoading.setValue(false);

                            List<String> trackListResponse = new ArrayList<>();
                            for (TrackEntity trackEntity : trackEntities) {
                                favoriteTracksIds.add(trackEntity.getTrackId());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            // handle the error case
                            //Yet, do not do nothing in this app
                            isDataLoading.setValue(false);
                            System.out.println(e.toString());
                        }

                        @Override
                        public void onComplete() {
                            isDataLoading.setValue(false);
                        }
                    }));

        }
        return favoriteTracksIds;
    }

    public void getAuthorizationToContactSpotifyAPI() {
        isDataLoading.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(topSongsDisplayRepository.getAuthorizationToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<AuthorizationResponse>() {

                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onSuccess(AuthorizationResponse authorizationResponse) {
                        isDataLoading.setValue(false);
                        authorizationToken.setExpiresAt(authorizationResponse.getExpiresInSec());
                        authorizationToken.setTokenBearer(authorizationResponse.getAccessToken());
                        authorizationToken.setTokenType(authorizationResponse.getTokenType());
                        Log.i("token1", "ok" + authorizationToken.getTokenBearer());
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        //Yet, do not do nothing in this app
                        isDataLoading.setValue(false);
                        System.out.println(e.toString());
                    }
                }));
    }

    //TODO : handle loader
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void searchTopPlayist() {
        isDataLoading.postValue(true);
        compositeDisposable.clear();

        // Get favorites tracks from database
        List<String> favoriteTracks = getFavoriteTracks();

        compositeDisposable.add(topSongsDisplayRepository.getDailyTopPlayistResponse(authorizationToken.getTokenAuthorization())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<TopTracksResponse>() {

                    @Override
                    public void onSuccess(TopTracksResponse topTracksResponse) {
                        isDataLoading.setValue(false);
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
                        //Yet, do not do nothing in this app
                        isDataLoading.setValue(false);
                        System.out.println(e.toString());
                    }
                }));
    }


    public void cancelSubscription() {
        compositeDisposable.clear();
        isDataLoading.setValue(false);
    }
}
