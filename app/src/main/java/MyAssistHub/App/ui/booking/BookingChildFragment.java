package MyAssistHub.App.ui.booking;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import MyAssistHub.App.adapter.AdapterBooking;
import MyAssistHub.App.database.BookingTable;
import MyAssistHub.App.databinding.FragmentBookingChildBinding;
import MyAssistHub.App.model.Booking;

/**
 * Created by Budiliauw87 on 2022-07-20.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
public class BookingChildFragment extends Fragment {

    public static final String ARG_PARAM1 = "argText";
    private int statusBooking = 0;
    private List<Booking> bookingList;
    private AdapterBooking adapterBooking;
    private FragmentBookingChildBinding binding;
    private BookingTable bookingTable;

    public BookingChildFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBookingChildBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        if (binding != null) {
            Bundle args = getArguments();
            statusBooking = args.getInt(ARG_PARAM1);
            Log.e("BookingChild","posisi "+statusBooking);
            bookingList = new ArrayList<>();
            adapterBooking = new AdapterBooking(bookingList, new AdapterBooking.ClickItemListener() {
                @Override
                public void onCompleted(Booking booking) {
                    if(booking!=null){
                        bookingTable.updateBooking(booking.getId(),1);
                        updateListBooking(booking);
                    }
                }

                @Override
                public void onCanceled(Booking booking) {
                    if(booking!=null){
                        bookingTable.updateBooking(booking.getId(),2);
                        updateListBooking(booking);
                    }

                }
            });
            binding.rcBooking.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            binding.rcBooking.setAdapter(adapterBooking);

            binding.swipeRefresh.setOnRefreshListener(() -> {
                binding.rcBooking.setVisibility(View.GONE);
                bookingList.clear();
                getDataBooking();
            });
            binding.swipeRefresh.post(()->{
                binding.swipeRefresh.setRefreshing(true);
                new Handler().postDelayed(()->{
                    getDataBooking();
                },1000);
            });

        }
    }
    //update data booking list
    private void updateListBooking(final Booking booking){
        int currentIndex = -1;
        for (int i = 0; i < bookingList.size(); i++) {
            currentIndex = i;
            Booking tempBooking = bookingList.get(i);
            if (tempBooking.getId() == booking.getId()) {
                bookingList.remove(i);
            }
        }
        if(currentIndex != -1){
            adapterBooking.notifyDataSetChanged();
        }
    }

    private void getDataBooking() {
        try {
            if (bookingTable == null) {
                bookingTable = new BookingTable(getActivity());
            }
            bookingList.addAll(bookingTable.getList(statusBooking));
            adapterBooking.notifyDataSetChanged();
            if (binding.swipeRefresh.isRefreshing()) {
                binding.swipeRefresh.setRefreshing(false);
            }
            if (bookingList.size() > 0) {
                binding.rcBooking.setVisibility(View.VISIBLE);
            } else {
                binding.rcBooking.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
