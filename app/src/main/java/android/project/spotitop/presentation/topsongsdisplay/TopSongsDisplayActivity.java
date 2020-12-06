package android.project.spotitop.presentation.topsongsdisplay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.project.spotitop.R;
import android.project.spotitop.presentation.topsongsdisplay.research.fragment.DailyTopFragment;

public class TopSongsDisplayActivity extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPagerAndTabs();
    }

    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.tab_viewpager);

        final DailyTopFragment dailyTopFragment = DailyTopFragment.newInstance();
        //final FavoriteFragment favoriteFragment = FavoriteFragment.newInstance();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                //if (position == 0) {
                    return dailyTopFragment;
                //}
                //return favoriteFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                //if (position == 0) {
                    return DailyTopFragment.TAB_NAME;
                //}
                //return FavoriteFragment.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 1;
            }
        });
    }
}