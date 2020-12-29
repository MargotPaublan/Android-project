package android.project.spotitop.presentation.topsongsdisplay.favorite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.project.spotitop.R;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class TrackDetailsAdapter extends RecyclerView.Adapter<TrackDetailsAdapter.TrackDetailsViewHolder>{

    public static class TrackDetailsViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView artistsTextView;
        private TextView albumTextView;
        private TextView durationTextView;
        private ImageView iconImageView;
        private View v;
        private TrackDetailViewItem trackDetailsViewItem;
        private TrackDetailsActionInterface trackDetailsActionInterface;
        private Button favoriteButton;

        public TrackDetailsViewHolder(View v, final TrackDetailsActionInterface trackDetailsActionInterface) {
            super(v);
            this.v = v;
            titleTextView = v.findViewById(R.id.track_title_textview);
            albumTextView = v.findViewById(R.id.track_album_textview);
            durationTextView = v.findViewById(R.id.track_duration_textview);
            artistsTextView = v.findViewById(R.id.track_artists_textview);
            iconImageView = v.findViewById(R.id.track_icon_imageview);
            favoriteButton = v.findViewById(R.id.track_button_favorite);
            setupListeners();
            this.trackDetailsActionInterface = trackDetailsActionInterface;
        }

        private void setupListeners() {
            favoriteButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //todo : changer le 'true'
                    trackDetailsActionInterface.removeTrackFromFavorites(trackDetailsViewItem.getTrackId());
                }
            });
        }

        void bind(TrackDetailViewItem trackDetailViewItem) {
            this.trackDetailsViewItem = trackDetailViewItem;
            titleTextView.setText(trackDetailViewItem.getTrackTitle());
            artistsTextView.setText(trackDetailViewItem.getTrackArtists());
            albumTextView.setText(trackDetailViewItem.getTrackAlbum());
            durationTextView.setText(trackDetailViewItem.getTrackDuration());

            /*favoriteButton.setChecked(true);
            if (trackDetailViewItem.getBookDescription() == null) {
                durationTextView.setVisibility(View.GONE);
            } else {
                durationTextView.setVisibility(View.VISIBLE);
            }*/


            Glide.with(v)
                    .load(trackDetailViewItem.getIconUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(iconImageView);

        }

    }

    private List<TrackDetailViewItem> trackDetailsViewItemList;
    private TrackDetailsActionInterface trackDetailsActionInterface;

    // Provide a suitable constructor (depends on the kind of dataset)
    public TrackDetailsAdapter(TrackDetailsActionInterface trackDetailsActionInterface) {
        this.trackDetailsViewItemList = new ArrayList<>();
        this.trackDetailsActionInterface = trackDetailsActionInterface;
    }

    public void bindViewModels(List<TrackDetailViewItem> trackItemsViewModelList) {
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
                .inflate(R.layout.item_detailed_track, parent, false);
        TrackDetailsViewHolder trackDetailsViewHolder = new TrackDetailsViewHolder(v, trackDetailsActionInterface);
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
