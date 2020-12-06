package android.project.spotitop.presentation.topsongsdisplay.research.fragment;

import android.os.Bundle;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackActionInterface;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackAdapter;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackViewItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DailyTopFragment extends Fragment implements TrackActionInterface {
    public static final String TAB_NAME = "Daily tracks top";
    private View rootView;
    private Spinner spinnerNbOfTracksView;
    private Button buttonRefreshView
    private RecyclerView recyclerView;
    private TrackAdapter trackAdapter;
    private ProgressBar progressBar;
    private TrackResearchViewModel trackResearchViewModel;
    //private BookFavoriteViewModel bookFavoriteViewModel;

    private DailyTopFragment() {
    }

    public static DailyTopFragment newInstance() {
        return new DailyTopFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //setupResearchView();
        setupRecyclerView();
        progressBar = rootView.findViewById(R.id.progress_bar);

        registerViewModels();
    }

    private void registerViewModels() {
        TrackResearchViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(TrackResearchViewModel.class);
        //bookFavoriteViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(BookFavoriteViewModel.class);
        //System.out.println("FVVM is " + bookFavoriteViewModel);

        //ici ?
        trackResearchViewModel.getTopTracks().observe(getViewLifecycleOwner(), new Observer<List<TrackViewItem>>() {
            @Override
            public void onChanged(List<TrackViewItem> trackViewItemList) {
                trackAdapter.bindViewModels(trackViewItemList);
            }
        });

        //Todo : vérifier
        TrackResearchViewModel.getIsDataLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isDataLoading) {
                progressBar.setVisibility(isDataLoading ? View.VISIBLE : View.GONE);
            }
        });
    }

    // todo : à faire
    private void setupResearchView() {
        searchView = rootView.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private Timer timer = new Timer();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String s) {
                if (s.length() == 0) {
                    bookSearchViewModel.cancelSubscription();
                } else {
                    timer.cancel();
                    timer = new Timer();
                    int sleep = 350;
                    if (s.length() == 1)
                        sleep = 5000;
                    else if (s.length() <= 3)
                        sleep = 300;
                    else if (s.length() <= 5)
                        sleep = 200;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            bookSearchViewModel.searchBooks(s);
                        }
                    }, sleep);
                }
                return true;
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        trackAdapter = new TrackAdapter(this);
        recyclerView.setAdapter(trackAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    // todo : à faire
    @Override
    public void onFavoriteToggle(String bookId, boolean isFavorite) {
        //Handle add and deletion to favorites
        if (isFavorite) {
            bookFavoriteViewModel.addBookToFavorite(bookId);
        }
        else {
            bookFavoriteViewModel.removeBookFromFavorite(bookId);
        }
    }
}
