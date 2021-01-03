package android.project.spotitop.presentation.viewmodel;

import android.os.Build;
import android.project.spotitop.data.api.serialization.AuthorizationResponse;
import android.project.spotitop.data.api.serialization.Item;
import android.project.spotitop.data.api.serialization.TopTracksResponse;
import android.project.spotitop.data.api.serialization.Track;
import android.project.spotitop.data.database.TrackEntity;
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

    private List<TrackViewItem> allTracks = new ArrayList<TrackViewItem>();

    private MutableLiveData<List<TrackViewItem>> tracksToDisplay = new MutableLiveData<List<TrackViewItem>>();
    private int nbOfTracksToDisplay;
    public MutableLiveData<List<TrackViewItem>> getTracksToDisplay() { return tracksToDisplay; }

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

                        for (Item item : topTracksResponse.getListOfTracks().getItems()) {
                            for (String id : favoriteTracks) {
                                if (id.equals(item.getTrack().getId())) {
                                    item.getTrack().setFavorite(true);
                                }
                            }
                            trackListResponse.add(item.getTrack());
                        }

                        allTracks = trackToTrackViewItemMapper.map(trackListResponse);
                        tracksToDisplay.setValue(allTracks.subList(0, nbOfTracksToDisplay));
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        System.out.println(e.toString());
                    }
                }));
    }

    public void setNbOfTracksToDisplay(int nbOfTracksToDisplay) {
        this.nbOfTracksToDisplay = nbOfTracksToDisplay;

        if (allTracks.size() == 50) {
            tracksToDisplay.setValue(allTracks.subList(0, nbOfTracksToDisplay));
        }

    }

    public void changeFavoriteState(String trackId, boolean isFavorite) {
        for (TrackViewItem trackViewItem : allTracks) {
            if (trackId.equals(trackViewItem.getTrackId())) {
                trackViewItem.setFavorite(isFavorite);
                tracksToDisplay.setValue(allTracks.subList(0, nbOfTracksToDisplay));
                break;
            }
        }
    }
}
