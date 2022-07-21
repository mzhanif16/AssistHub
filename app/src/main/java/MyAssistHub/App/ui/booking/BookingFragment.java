package MyAssistHub.App.ui.booking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import MyAssistHub.App.adapter.AdapterViewPager;
import MyAssistHub.App.databinding.FragmentBookingBinding;

public class BookingFragment extends Fragment {
    private AdapterViewPager adapterViewPager;
    private ArrayList<String> tabList;

    private FragmentBookingBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBookingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapterViewPager = new AdapterViewPager(getActivity());
        binding.viewPager.setAdapter(adapterViewPager);
        tabList = new ArrayList<>();

        tabList.add("Upcomming");
        tabList.add("Completed");
        tabList.add("Canceled");
        new TabLayoutMediator(binding.tablayout, binding.viewPager, (tab, position) -> tab.setText(tabList.get(position))).attach();

    }
}
