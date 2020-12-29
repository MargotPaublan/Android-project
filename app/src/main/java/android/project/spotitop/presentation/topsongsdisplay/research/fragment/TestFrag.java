package android.project.spotitop.presentation.topsongsdisplay.research.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.project.spotitop.R;
import android.project.spotitop.data.di.FakeDependencyInjection;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackActionInterface;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackAdapter;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackViewItem;
import android.project.spotitop.presentation.viewmodel.DailyTopTracksViewModel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.List;

public class TestFrag extends Fragment implements TrackActionInterface  {
    /*

    public TestFrag() {
        super(R.layout.fragment_top_tracks_listing);
    }*/

    public static final String TAB_NAME = "Daily top tracks";
    private View rootView;
    private Spinner spinnerNbOfTracksView;
    private ImageButton imageButtonSearchView;
    private RecyclerView recyclerView;
    private TrackAdapter trackAdapter;
    private ProgressBar progressBar;
    private DailyTopTracksViewModel dailyTopTracksViewModel;
    //private BookFavoriteViewModel bookFavoriteViewModel;

    private TestFrag() {
    }

    public static TestFrag newInstance() {
        return new TestFrag();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_top_tracks_listing, container, false);
        return rootView;
    }




    @Override
    public void onFavoriteButton(String trackId, boolean isFavorite) {
        //Handle add and deletion to favorites
        /*if (isFavorite) {
            bookFavoriteViewModel.addBookToFavorite(bookId);
        }
        else {
            bookFavoriteViewModel.removeBookFromFavorite(bookId);
        }*/
    }
}
