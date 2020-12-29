package android.project.spotitop.presentation.topsongsdisplay.favorite.fragment;

import android.os.Bundle;
import android.project.spotitop.data.di.FakeDependencyInjection;
import android.project.spotitop.presentation.topsongsdisplay.favorite.adapter.TrackDetailViewItem;
import android.project.spotitop.presentation.topsongsdisplay.favorite.adapter.TrackDetailsActionInterface;
import android.project.spotitop.presentation.topsongsdisplay.favorite.adapter.TrackDetailsAdapter;
import android.project.spotitop.presentation.viewmodel.Event;
import android.project.spotitop.presentation.viewmodel.TrackFavoriteViewModel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.project.spotitop.R;

import java.util.List;

public class FavoriteFragment extends Fragment implements TrackDetailsActionInterface{
    public static final String TAB_NAME = "Favorites";
    private View rootView;
    private RecyclerView recyclerView;
    private TrackDetailsAdapter trackDetailsAdapter;
    private TrackFavoriteViewModel trackFavoriteViewModel;

    private FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_favorite_tracks, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        registerViewModels();
    }

    private void registerViewModels() {
        trackFavoriteViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(TrackFavoriteViewModel.class);
        System.out.println("FVVM is " + trackFavoriteViewModel);

        trackFavoriteViewModel.getFavoriteTracks().observe(getViewLifecycleOwner(), new Observer<List<TrackDetailViewItem>>() {
            @Override
            public void onChanged(List<TrackDetailViewItem> trackDetailsViewItemList) {
                trackDetailsAdapter.bindViewModels(trackDetailsViewItemList);
            }
        });

        trackFavoriteViewModel.getTrackAddedEvent().observe(getViewLifecycleOwner(), new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                //Do nothing
            }
        });

        trackFavoriteViewModel.getTrackDeletedEvent().observe(getViewLifecycleOwner(), new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                //Do nothing
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        trackDetailsAdapter = new TrackDetailsAdapter(this);
        recyclerView.setAdapter(trackDetailsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //bookFavoritePresenter.detachView();
    }



    @Override
    public void removeTrackFromFavorites(String trackId) {
        trackFavoriteViewModel.removeTrackFromFavorites(trackId);
    }
}
