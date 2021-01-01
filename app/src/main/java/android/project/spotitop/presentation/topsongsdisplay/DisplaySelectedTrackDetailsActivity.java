package android.project.spotitop.presentation.topsongsdisplay;

import android.os.Bundle;
import android.project.spotitop.R;
import android.project.spotitop.presentation.topsongsdisplay.research.adapter.TrackViewItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class DisplaySelectedTrackDetailsActivity extends AppCompatActivity {
    private TrackViewItem trackViewItem;
    private ImageButton backButton;
    private ImageButton favoriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);

        this.trackViewItem = (TrackViewItem) getIntent().getSerializableExtra("TrackViewItem");

        TextView titleTextView = findViewById(R.id.track_title_textview);
        TextView artistsTextView = findViewById(R.id.track_artists_textview);
        TextView albumTextView = findViewById(R.id.track_album_textview);
        TextView rankTextView = findViewById(R.id.track_rank_textview);
        TextView durationMsTextView = findViewById(R.id.track_duration_textview);
        TextView releaseDateTextView = findViewById(R.id.track_upload_date_textview);
        ImageView iconImageView = findViewById(R.id.track_icon_imageview);
        backButton = findViewById(R.id.back_button);
        favoriteButton = findViewById(R.id.track_button_favorite);



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


        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteButton.setActivated(!favoriteButton.isActivated());
            }
        });
    }


}
