package android.project.spotitop.presentation.topsongsdisplay.research.adapter;

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



public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder>{
    public static final int LIST_VIEW = 0;
    public static final int GRID_VIEW = 1;
    boolean isSwitchView = true;
    private static RecyclerViewClickListener itemClickListener;



    private List<TrackViewItem> trackViewItemList;
    private TrackActionInterface trackActionInterface;


    // Provide a suitable constructor (depends on the kind of dataset)
    public TrackAdapter(TrackActionInterface trackActionInterface, RecyclerViewClickListener itemClickListener) {
        trackViewItemList = new ArrayList<>();
        this.trackActionInterface = trackActionInterface;
        this.itemClickListener = itemClickListener;
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
        View v;
        TrackViewHolder trackViewHolder = null;

        if (viewType == LIST_VIEW) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_track, parent, false);
        }
        else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_track_grid, parent, false);
        }

        trackViewHolder = new TrackViewHolder(v, trackActionInterface);
        return trackViewHolder;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        holder.bind(trackViewItemList.get(position));
    }


    @Override
    public int getItemViewType (int position) {
        if (isSwitchView){
            return LIST_VIEW;
        }else{
            return GRID_VIEW;
        }
    }

    public boolean toggleItemViewType () {
        isSwitchView = !isSwitchView;
        return isSwitchView;
    }


    // Return the size of the dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return trackViewItemList.size();
    }


    public static class TrackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView rankTextView;
        private TextView trackNameTextView;
        private TextView artistsTextView;
        private TextView albumTextView;
        private ImageView iconImageView;
        private View v;

        private TrackViewItem trackViewItem;
        private TrackActionInterface trackActionInterface;
        private ImageButton favoriteButton;

        public TrackViewHolder(View v, final TrackActionInterface trackActionInterface) {
            super(v);
            this.v = v;
            this.trackActionInterface = trackActionInterface;

            // Retrieves views of screen elements
            rankTextView = v.findViewById(R.id.track_rank_textview);
            trackNameTextView = v.findViewById(R.id.track_title_textview);
            artistsTextView = v.findViewById(R.id.track_artists_textview);
            albumTextView = v.findViewById(R.id.track_album_textview);
            iconImageView = v.findViewById(R.id.track_icon_imageview);
            favoriteButton = v.findViewById(R.id.track_button_favorite);


            setupListeners();
            this.v.setOnClickListener(this);
        }

        private void setupListeners() {
            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favoriteButton.setActivated(!favoriteButton.isActivated());
                    trackActionInterface.onFavoriteButton(trackViewItem.getTrackId(), favoriteButton.isActivated());
                }
            });

        }

        void bind(final TrackViewItem trackViewItem) {
            this.trackViewItem = trackViewItem;
            rankTextView.setText(trackViewItem.getTrackRank() + ".");
            trackNameTextView.setText(trackViewItem.getTrackName());
            artistsTextView.setText(trackViewItem.getTrackArtists());
            albumTextView.setText(trackViewItem.getTrackAlbum());

            Glide.with(v)
                    .load(trackViewItem.getTrackImageUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(iconImageView);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.recyclerViewListClicked(v, this.getLayoutPosition());
        }
    }
}
