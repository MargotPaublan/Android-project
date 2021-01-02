package android.project.spotitop.presentation.viewmodel;

import android.os.Build;
import android.project.spotitop.data.api.serialization.AuthorizationResponse;
import android.project.spotitop.data.api.serialization.Item;
import android.project.spotitop.data.api.serialization.TopTracksResponse;
import android.project.spotitop.data.api.serialization.Track;
import android.project.spotitop.data.repository.topsongsdisplay.TopSongsDisplayRepository;
import android.project.spotitop.data.repository.topsongsdisplay.remote.AuthorizationToken;
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

public class DailyTopTracksViewModel extends ViewModel {
    private TopSongsDisplayRepository topSongsDisplayRepository;
    private CompositeDisposable compositeDisposable;
    private TrackToTrackViewItemMapper trackToTrackViewItemMapper;
    private AuthorizationToken authorizationToken;

    public DailyTopTracksViewModel(TopSongsDisplayRepository topSongsDisplayRepository) {
        this.topSongsDisplayRepository = topSongsDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.trackToTrackViewItemMapper = new TrackToTrackViewItemMapper();
        this.authorizationToken = AuthorizationToken.getInstance();
    }

    private MutableLiveData<List<TrackViewItem>> tracks = new MutableLiveData<List<TrackViewItem>>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();

    public MutableLiveData<List<TrackViewItem>> getTracks() {
        return tracks;
    }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
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

        /*if (authorizationToken.getTokenBearer() == null || authorizationToken.isExpired()) {
            getAuthorizationToContactSpotifyAPI();
        }*/

        //compositeDisposable.clear();

        //Log.i("token", "ok" + authorizationToken.getTokenBearer());
        compositeDisposable.add(topSongsDisplayRepository.getDailyTopPlayistResponse(authorizationToken.getTokenAuthorization())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<TopTracksResponse>() {

                    @Override
                    public void onSuccess(TopTracksResponse topTracksResponse) {
                        isDataLoading.setValue(false);

                        List<Track> trackListResponse = new ArrayList<>();
                        for (Item item : topTracksResponse.getListOfTracks().getItems()) {
                            trackListResponse.add(item.getTrack());
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
