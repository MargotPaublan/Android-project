package android.project.spotitop.presentation.viewmodel;


import android.project.spotitop.data.repository.topsongsdisplay.TopSongsDisplayRepository;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final TopSongsDisplayRepository topSongsDisplayRepository;

    public ViewModelFactory(TopSongsDisplayRepository topSongsDisplayRepository) {
        this.topSongsDisplayRepository = topSongsDisplayRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DailyTopTracksViewModel.class)) {
            return (T) new DailyTopTracksViewModel(topSongsDisplayRepository);
        } else {
            return (T) new TrackFavoriteViewModel(topSongsDisplayRepository);
        }


    }
}
