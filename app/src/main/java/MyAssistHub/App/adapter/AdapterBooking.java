package MyAssistHub.App.adapter;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import MyAssistHub.App.databinding.RowItemBookingBinding;
import MyAssistHub.App.helpers.DummyHelpers;
import MyAssistHub.App.model.Booking;
import MyAssistHub.App.model.Categories;


public class AdapterBooking extends RecyclerView.Adapter<AdapterBooking.ItemViewHolder> {
    private List<Booking> bookingList;
    private ClickItemListener listener;
    private ArrayList<Categories> listCategories;

    public AdapterBooking(List<Booking> bookingList, ClickItemListener clickItemListener) {
        this.bookingList = bookingList;
        this.listener = clickItemListener;
        listCategories = DummyHelpers.getCategories();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(RowItemBookingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(bookingList.get(position));

    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        RowItemBookingBinding binding;

        public ItemViewHolder(@NonNull RowItemBookingBinding rowItemBookingBinding) {
            super(rowItemBookingBinding.getRoot());
            binding = rowItemBookingBinding;
        }

        void bind(Booking booking) {
            binding.textView8.setText(booking.getServiceName()); //service name
            binding.tvuser.setText(booking.getUserName()); //username
            final int status  = booking.getStatus();
            if(status == 0){
                binding.textView9.setText("Upcomming");
                binding.tvcancelbooking.setVisibility(VISIBLE);
                binding.tvcompletebooking.setVisibility(VISIBLE);
            }else if(status == 1){
                binding.textView9.setText("Completed");
                binding.tvcancelbooking.setVisibility(GONE);
                binding.tvcompletebooking.setVisibility(GONE);
            }else if( status == 2 ){
                binding.textView9.setText("Canceled");
                binding.tvcancelbooking.setVisibility(GONE);
                binding.tvcompletebooking.setVisibility(GONE);
            }

            binding.tvtime.setText(booking.getHourBooking());
            binding.tvdate.setText(booking.getDateBooking());
            binding.tvcancelbooking.setOnClickListener((v)->listener.onCanceled(booking));
            binding.tvcompletebooking.setOnClickListener((v)->listener.onCompleted(booking));

            for(int i=0; i<listCategories.size();i++){
               if(listCategories.get(i).getCategories().equals(booking.getCategory())){
                   Glide.with(itemView.getContext())
                           .load(listCategories.get(i).getImage())
                           .into(binding.imageView7);
                   break;
               }
            }
        }
    }

    public interface ClickItemListener {
        void onCompleted(Booking booking);
        void onCanceled(Booking booking);
    }
}
