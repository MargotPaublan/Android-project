package android.project.spotitop.presentation.topsongsdisplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Debug;
import android.project.spotitop.R;
import android.project.spotitop.presentation.topsongsdisplay.favorite.fragment.FavoriteFragment;
import android.project.spotitop.presentation.topsongsdisplay.research.fragment.DailyTopFragment;


public class TopSongsDisplayActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve activity's view
        setContentView(R.layout.activity_main);

        // Setup a viewpager with a top tracks listing fragment and a favorite fragment in this activity
        setupViewPagerAndTabs();
    }

    /**
     * Setup a viewpager with 2 tabs : on containing the top tracks listing fragment, and the other the favorite fragment, in this activity.
     *
     */
    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.tab_viewpager);

        // the top tracks listing fragment
        final DailyTopFragment dailyTopFragment = DailyTopFragment.newInstance();
        // the favorite fragment
        final FavoriteFragment favoriteFragment = FavoriteFragment.newInstance();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return dailyTopFragment;
                }
                else {
                    return favoriteFragment;
                }

            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return DailyTopFragment.TAB_NAME;
                }
                else {
                    return FavoriteFragment.TAB_NAME;
                }

            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }



}