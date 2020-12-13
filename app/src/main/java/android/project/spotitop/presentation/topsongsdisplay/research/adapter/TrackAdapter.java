package android.project.spotitop.presentation.topsongsdisplay.research.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.project.spotitop.R;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder>{
    public static class TrackViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView authorsTextView;
        private TextView albumTextView;
        private TextView durationTextView;
        private ImageView iconImageView;
        private View v;
        private TrackViewItem trackViewItem;
        private TrackActionInterface trackActionInterface;
        //private Switch favoriteSwitch;

        public TrackViewHolder(View v, final TrackActionInterface trackActionInterface) {
            super(v);
            this.v = v;
            titleTextView = v.findViewById(R.id.track_title_textview);
            authorsTextView = v.findViewById(R.id.track_artists_textview);
            albumTextView = v.findViewById(R.id.track_album_textview);
            durationTextView = v.findViewById(R.id.track_duration_textview);
            iconImageView = v.findViewById(R.id.track_icon_imageview);
            //favoriteSwitch = v.findViewById(R.id.favorite_switch);
            this.trackActionInterface = trackActionInterface;
            setupListeners();
        }

        private void setupListeners() {
            /*favoriteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    bookActionInterface.onFavoriteToggle(bookViewItem.getBookId(), b);
                }
            });*/
        }

        void bind(final TrackViewItem trackViewItem) {
            this.trackViewItem = trackViewItem;
            titleTextView.setText(trackViewItem.getTrackName());
            authorsTextView.setText(trackViewItem.getArtistsToString());
            albumTextView.setText(trackViewItem.getAlbumName());
            durationTextView.setText(trackViewItem.getTrackDuration());
            //favoriteSwitch.setChecked(trackViewItem.isFavorite());
            Glide.with(v)
                    .load(trackViewItem.getAlbumImgUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(iconImageView);
        }

    }

    private List<TrackViewItem> trackViewItemList;
    private TrackActionInterface trackActionInterface;

    // Provide a suitable constructor (depends on the kind of dataset)
    public TrackAdapter(TrackActionInterface trackActionInterface) {
        trackViewItemList = new ArrayList<>();
        this.trackActionInterface = trackActionInterface;
    }

    public void bindViewModels(List<TrackViewItem> trackViewItemList) {
        this.trackViewItemList.clear();
        this.trackViewItemList.addAll(trackViewItemList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_track, parent, false);
        TrackViewHolder trackViewHolder = new TrackViewHolder(v, trackActionInterface);
        return trackViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        holder.bind(trackViewItemList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return trackViewItemList.size();
    }
}
