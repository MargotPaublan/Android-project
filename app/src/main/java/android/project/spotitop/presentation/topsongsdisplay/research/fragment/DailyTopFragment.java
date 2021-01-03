package android.project.spotitop.presentation.topsongsdisplay.research.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.project.spotitop.data.di.FakeDependencyInjection;
import android.project.spotitop.presentation.topsongsdisplay.DisplaySelectedTrackDetailsActivity;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.RecyclerViewClickListener;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackActionInterface;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackAdapter;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackViewItem;
import android.project.spotitop.presentation.viewmodel.DailyTopTracksViewModel;
import android.project.spotitop.presentation.viewmodel.TrackFavoriteViewModel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.project.spotitop.R;
import android.widget.TextView;
import java.util.List;



public class DailyTopFragment extends Fragment implements TrackActionInterface, RecyclerViewClickListener {
    public static final String TAB_NAME = "Daily top tracks";
    private View rootView;
    private Spinner spinnerNbOfTracksView;
    private ImageButton imageButtonSearchView;
    private ImageButton imageButtonGridView;
    private ImageButton imageButtonListView;

    private RecyclerView recyclerView;
    private TrackAdapter trackAdapter;

    private TextView spinnerLabelTextView;
    private DailyTopTracksViewModel dailyTopTracksViewModel;
    private TrackFavoriteViewModel trackFavoriteViewModel;
    private List<TrackViewItem> trackItemViewModelList;

    private DailyTopFragment() {
    }

    public static DailyTopFragment newInstance() {
        return new DailyTopFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_top_tracks_listing, container, false);
        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Retrieves layout of screen elements
        retrieveLayoutOfElements();

        // Setup recycler view for the displayed list of tracks
        setupRecyclerView();

        // Registers different viewmodels
        registerViewModels();




        setupSpinner();
        setupListeners();
        dailyTopTracksViewModel.getAuthorizationToContactSpotifyAPI();
    }


    /**
     * Retrieve views of different screen elements with which the user can interract
     */
    public void retrieveLayoutOfElements() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        imageButtonSearchView = rootView.findViewById(R.id.button_search_view);
        imageButtonGridView = rootView.findViewById(R.id.imagebutton_grid_view);
        imageButtonListView = rootView.findViewById(R.id.imagebutton_list_view);
        spinnerNbOfTracksView = rootView.findViewById(R.id.top_number_spinner);
        spinnerLabelTextView = rootView.findViewById(R.id.spinner_label_textview);
    }


    private void registerViewModels() {
        dailyTopTracksViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(DailyTopTracksViewModel.class);
        trackFavoriteViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(TrackFavoriteViewModel.class);

        dailyTopTracksViewModel.getTracks().observe(getViewLifecycleOwner(), new Observer<List<TrackViewItem>>() {
            @Override
            public void onChanged(List<TrackViewItem> trackItemViewModelListResults) {
                trackItemViewModelList = trackItemViewModelListResults;
                int nbOfTracksToDisplay = Integer.parseInt(spinnerNbOfTracksView.getSelectedItem().toString());
                List<TrackViewItem> sizedTrackViewModelList = trackItemViewModelList.subList(0, nbOfTracksToDisplay);
                trackAdapter.bindViewModels(sizedTrackViewModelList);
            }


        });

    }


    /**
     * Setup recycler view to display the list of tracks.
     * By default, we set the layout manager to be a list of elements.
     */
    private void setupRecyclerView() {
        trackAdapter = new TrackAdapter(this, this);
        recyclerView.setAdapter(trackAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    public void recyclerViewListClicked(View v, int position) {
        TrackViewItem trackViewItem = trackItemViewModelList.get(position);
        Intent intent = new Intent(this.getContext(), DisplaySelectedTrackDetailsActivity.class);
        intent.putExtra("TrackViewItem", trackViewItem);
        startActivity(intent);
    }




    public void setupListeners() {
        // Button "search" click listener
        imageButtonSearchView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {
                dailyTopTracksViewModel.searchTopPlayist();
            }
        });

        // Button "grid" click listener
        imageButtonGridView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                imageButtonGridView.setVisibility(v.GONE);
                imageButtonListView.setVisibility(v.VISIBLE);

                boolean isSwitched = trackAdapter.toggleItemViewType();
                recyclerView.setLayoutManager(isSwitched ? new LinearLayoutManager(getContext()) : new GridLayoutManager(getContext(), 2));
            }
        });


        // Button "list" click listener
        imageButtonListView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                imageButtonListView.setVisibility(v.GONE);
                imageButtonGridView.setVisibility(v.VISIBLE);
                boolean isSwitched = trackAdapter.toggleItemViewType();
                recyclerView.setLayoutManager(isSwitched ? new LinearLayoutManager(getContext()) : new GridLayoutManager(getContext(), 2));
            }
        });


        // Spinner selection listener : the number of tracks displayed in the list depends of the spinner selection
        spinnerNbOfTracksView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int nbOfTracksToDisplay = Integer.parseInt(spinnerNbOfTracksView.getItemAtPosition(position).toString());

                if (trackItemViewModelList != null) {
                    List<TrackViewItem> sizedTrackViewModelList = trackItemViewModelList.subList(0, nbOfTracksToDisplay);
                    trackAdapter.bindViewModels(sizedTrackViewModelList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing
            }
        });

    }

    /**
     * Setup the spinner. The spinner selection determines how many tracks must be displayed on screen (in the recycler view).
     * We set 4 different numbers of tracks to be displayed : 3, 5, 10 and 50.
     */
    public void setupSpinner() {
        // Spinner setup
        String[] nbOfTracks = { "3", "5", "10", "50"};

        // Spinner adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), R.layout.simple_spinner_item, nbOfTracks);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinnerNbOfTracksView.setAdapter(adapter);

        // Spinner selection by default
        spinnerNbOfTracksView.setSelection(3);

        // Spinner label setup
        spinnerLabelTextView.setText("Top :");
    }


    
    @Override
    public void onFavoriteButton(String trackId, boolean isFavorite) {
        //Handle add and deletion to favorites

        TrackViewItem trackViewItem = getTrackViewItem(trackId);
        if (isFavorite) {
            trackViewItem.setFavorite(true);
            trackFavoriteViewModel.addTrackToFavorites(trackId);
        }
        else {
            trackViewItem.setFavorite(false);
            trackFavoriteViewModel.removeTrackFromFavorites(trackId);
        }
    }

    public TrackViewItem getTrackViewItem(String id) {
        for (TrackViewItem trackViewItem : trackItemViewModelList) {
            if (trackViewItem.getTrackId().equals(id)) {
                return trackViewItem;
            }
        }
        return null;
    }
}
