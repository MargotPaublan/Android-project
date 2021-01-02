package android.project.spotitop.presentation.topsongsdisplay.favorite.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.project.spotitop.R;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class FavoriteTrackAdapter extends RecyclerView.Adapter<FavoriteTrackAdapter.TrackDetailsViewHolder>{

    public static class TrackDetailsViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView artistsTextView;
        private TextView albumTextView;
        private TextView durationTextView;
        private TextView releaseDateTextView;
        private ImageView iconImageView;
        private View v;

        private TrackFavoriteViewItem trackDetailsViewItem;
        private FavoriteTrackActionInterface favoriteTrackActionInterface;
        private ImageButton favoriteButton;

        public TrackDetailsViewHolder(View v, final FavoriteTrackActionInterface favoriteTrackActionInterface) {
            super(v);
            this.v = v;
            titleTextView = v.findViewById(R.id.track_title_textview);
            albumTextView = v.findViewById(R.id.track_album_textview);
            durationTextView = v.findViewById(R.id.track_duration_textview);
            artistsTextView = v.findViewById(R.id.track_artists_textview);
            releaseDateTextView = v.findViewById(R.id.track_release_date_textview);
            iconImageView = v.findViewById(R.id.track_icon_imageview);
            favoriteButton = v.findViewById(R.id.track_button_favorite);
            setupListeners();
            this.favoriteTrackActionInterface = favoriteTrackActionInterface;
        }

        private void setupListeners() {
            favoriteButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //todo : changer le 'true'
                    favoriteTrackActionInterface.removeTrackFromFavorites(trackDetailsViewItem.getTrackId());
                }
            });
        }

        void bind(TrackFavoriteViewItem trackFavoriteViewItem) {
            this.trackDetailsViewItem = trackFavoriteViewItem;
            Log.i("tracktitle1", trackFavoriteViewItem.getTrackAlbum());
            titleTextView.setText(trackFavoriteViewItem.getTrackName());
            artistsTextView.setText(trackFavoriteViewItem.getTrackArtists());
            albumTextView.setText(trackFavoriteViewItem.getTrackAlbum());
            durationTextView.setText("Duration : " + trackFavoriteViewItem.getTrackDuration());
            releaseDateTextView.setText(" Release date : " + trackFavoriteViewItem.getTrackReleaseDate());
            favoriteButton.setActivated(true);

            Glide.with(v)
                    .load(trackFavoriteViewItem.getTrackIconUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(iconImageView);

        }

    }

    private List<TrackFavoriteViewItem> trackDetailsViewItemList;
    private FavoriteTrackActionInterface favoriteTrackActionInterface;

    // Provide a suitable constructor (depends on the kind of dataset)
    public FavoriteTrackAdapter(FavoriteTrackActionInterface favoriteTrackActionInterface) {
        this.trackDetailsViewItemList = new ArrayList<>();
        this.favoriteTrackActionInterface = favoriteTrackActionInterface;
    }

    public void bindViewModels(List<TrackFavoriteViewItem> trackItemsViewModelList) {
        this.trackDetailsViewItemList.clear();
        this.trackDetailsViewItemList.addAll(trackItemsViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TrackDetailsViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_track_favorite, parent, false);
        TrackDetailsViewHolder trackDetailsViewHolder = new TrackDetailsViewHolder(v, favoriteTrackActionInterface);
        return trackDetailsViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TrackDetailsViewHolder holder, int position) {
        holder.bind(trackDetailsViewItemList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return trackDetailsViewItemList.size();
    }
}
