package android.project.spotitop.presentation.topsongsdisplay;

import android.os.Bundle;
import android.project.spotitop.R;
import android.project.spotitop.data.di.FakeDependencyInjection;
import android.project.spotitop.presentation.topsongsdisplay.favorite.adapter.TrackFavoriteViewItem;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackViewItem;
import android.project.spotitop.presentation.viewmodel.DailyTopTracksViewModel;
import android.project.spotitop.presentation.viewmodel.Event;
import android.project.spotitop.presentation.viewmodel.TrackFavoriteViewModel;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

public class DisplaySelectedTrackDetailsActivity extends AppCompatActivity {
    private TrackViewItem trackViewItem;
    private ImageButton backButton;
    private ImageButton favoriteButton;
    private TrackFavoriteViewModel trackFavoriteViewModel;
    private DailyTopTracksViewModel dailyTopTracksViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);
        this.trackViewItem = (TrackViewItem) getIntent().getSerializableExtra("TrackViewItem");

        this.registerViewModels();
        this.bindDataAndViews();
        this.setupListeners();
    }

    private void bindDataAndViews() {
        // Retrieves views of differents fields of data
        TextView titleTextView = findViewById(R.id.track_title_textview);
        TextView artistsTextView = findViewById(R.id.track_artists_textview);
        TextView albumTextView = findViewById(R.id.track_album_textview);
        TextView rankTextView = findViewById(R.id.track_rank_textview);
        TextView durationMsTextView = findViewById(R.id.track_duration_textview);
        TextView releaseDateTextView = findViewById(R.id.track_upload_date_textview);
        ImageView iconImageView = findViewById(R.id.track_icon_imageview);
        backButton = findViewById(R.id.back_button);
        favoriteButton = findViewById(R.id.track_button_favorite);

        // Binds data to views
        titleTextView.setText(trackViewItem.getTrackName());
        artistsTextView.setText("Artist(s) : " + trackViewItem.getTrackArtists());
        albumTextView.setText("Album : " +  trackViewItem.getTrackAlbum());
        rankTextView.setText("Rank : " + trackViewItem.getTrackRank());
        durationMsTextView.setText("Duration : " + trackViewItem.getTrackDuration());
        releaseDateTextView.setText("Release date : " + trackViewItem.getTrackReleaseDate());
        Glide.with(this.getApplicationContext())
                .load(trackViewItem.getTrackImageUrl())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .circleCrop()
                .into(iconImageView);
    }



    private void setupListeners() {
        //TrackFavoriteViewModel trackFavoriteViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(TrackFavoriteViewModel.class);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteButton.setActivated(!favoriteButton.isActivated());

                if (!trackViewItem.isFavorite()) {
                    trackFavoriteViewModel.addTrackToFavorites(trackViewItem.getTrackId());
                }
                else {
                    trackFavoriteViewModel.removeTrackFromFavorites(trackViewItem.getTrackId());
                }
            }
        });
    }


    private void registerViewModels() {
        trackFavoriteViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(TrackFavoriteViewModel.class);
        //dailyTopTracksViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(DailyTopTracksViewModel.class);
        /*System.out.println("FVVM is " + trackFavoriteViewModel);

        trackFavoriteViewModel.getFavoriteTracks().observe(this, new Observer<List<TrackFavoriteViewItem>>() {
            @Override
            public void onChanged(List<TrackFavoriteViewItem> trackFavoriteViewItemList) {
                favoriteTrackAdapter.bindViewModels(trackFavoriteViewItemList);
            }
        });

        trackFavoriteViewModel.getTrackAddedEvent().observe(this, new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                //Do nothing
            }
        });

        trackFavoriteViewModel.getTrackDeletedEvent().observe(this, new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                //Do nothing
            }
        });*/
    }


}
