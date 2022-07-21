package MyAssistHub.App.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import MyAssistHub.App.ui.booking.BookingChildFragment;


public class AdapterViewPager extends FragmentStateAdapter {
    public AdapterViewPager(FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new BookingChildFragment();
        Bundle args = new Bundle();
        args.putInt(BookingChildFragment.ARG_PARAM1,position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
