package android.project.spotitop.presentation.viewmodel;

import android.project.spotitop.data.database.TrackEntity;
import android.project.spotitop.data.repository.topsongsdisplay.TopSongsDisplayRepository;
import android.project.spotitop.presentation.topsongsdisplay.favorite.adapter.TrackFavoriteViewItem;
import android.project.spotitop.presentation.topsongsdisplay.favorite.mapper.TrackEntityToTrackFavoriteViewItemMapper;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class TrackFavoriteViewModel extends ViewModel {
    private TopSongsDisplayRepository topSongsDisplayRepository;
    private CompositeDisposable compositeDisposable;
    private TrackEntityToTrackFavoriteViewItemMapper trackEntityToTrackFavoriteViewItemMapper;

    public TrackFavoriteViewModel(TopSongsDisplayRepository topSongsDisplayRepository) {
        this.topSongsDisplayRepository = topSongsDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.trackEntityToTrackFavoriteViewItemMapper = new TrackEntityToTrackFavoriteViewItemMapper();
    }

    private MutableLiveData<List<TrackFavoriteViewItem>> favoriteTracks;
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();
    final MutableLiveData<Event<String>> trackAddedEvent = new MutableLiveData<>();
    final MutableLiveData<Event<String>> trackDeletedEvent = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    //TODO : handle loader
    public  MutableLiveData<List<TrackFavoriteViewItem>> getFavoriteTracks() {
        isDataLoading.setValue(true);

        // If favoriteBooks is empty
        if (favoriteTracks == null) {
            favoriteTracks = new MutableLiveData<>();

            compositeDisposable.add(topSongsDisplayRepository.getSavedTracks()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new ResourceSubscriber<List<TrackEntity>>() {
                        @Override
                        public void onNext(List<TrackEntity> trackEntities) {
                            isDataLoading.setValue(false);
                            favoriteTracks.setValue(trackEntityToTrackFavoriteViewItemMapper.map(trackEntities));
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
        return favoriteTracks;
    }

    public MutableLiveData<Event<String>> getTrackAddedEvent() {
        return trackAddedEvent;
    }

    public MutableLiveData<Event<String>> getTrackDeletedEvent() {
        return trackDeletedEvent;
    }

    // todo : changer tokenBearer
    public void addTrackToFavorites(final String trackId){
        compositeDisposable.add(topSongsDisplayRepository.saveTrack(trackId, "Bearer BQB2VmRjOyRXF_gE7vVs40RB9VHMYS60elDAXYqBIMlaN9XBwIFNtTEV93_O8X7KZN0qnX5JYFptteykBBc")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        trackAddedEvent.setValue(new Event<>(trackId));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println(e.toString());
                    }
                }));
    }

    public void removeTrackFromFavorites(final String trackId){
        compositeDisposable.add(topSongsDisplayRepository.removeTrackFromDB(trackId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        trackDeletedEvent.setValue(new Event<>(trackId));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println(e.toString());
                    }
                }));
    }


}
