package android.project.spotitop.presentation.viewmodel;

import android.project.spotitop.data.api.serialization.PlayistResponse;
import android.project.spotitop.data.repository.topsongsdisplay.TopSongsDisplayRepository;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackViewItem;
import android.project.spotitop.presentation.topsongsdisplay.research.mapper.TrackToTrackViewItemMapper;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DailyTopTracksViewModel extends ViewModel {
    private TopSongsDisplayRepository topSongsDisplayRepository;
    private CompositeDisposable compositeDisposable;
    private TrackToTrackViewItemMapper trackToTrackViewItemMapper;

    public DailyTopTracksViewModel(TopSongsDisplayRepository topSongsDisplayRepository) {
        this.topSongsDisplayRepository = topSongsDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.trackToTrackViewItemMapper = new TrackToTrackViewItemMapper();
    }

    private MutableLiveData<List<TrackViewItem>> tracks = new MutableLiveData<List<TrackViewItem>>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();

    public MutableLiveData<List<TrackViewItem>> getTracks() {
        return tracks;
    }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    //TODO : handle loader
    public void searchTopPlayist() {
        isDataLoading.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(topSongsDisplayRepository.getDailyTopPlayistResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<PlayistResponse>() {

                    @Override
                    public void onSuccess(PlayistResponse playistResponse) {
                        isDataLoading.setValue(false);
                        //todo : bien faire le mapper et la réponse
                        tracks.setValue(trackToTrackViewItemMapper.map(playistResponse.getTracksList().getDailyTopTrack()));
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
