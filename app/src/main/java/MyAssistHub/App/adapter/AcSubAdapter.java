package MyAssistHub.App.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;

import MyAssistHub.App.databinding.ItemSubCategoriesBinding;
import MyAssistHub.App.helpers.Utils;
import MyAssistHub.App.model.Subcategory;

public class AcSubAdapter extends RecyclerView.Adapter<AcSubAdapter.AcsubViewHolder> {
    private ArrayList<Subcategory> listAcsubcategory;
    private ClickItemListener listener;
    public AcSubAdapter( ArrayList<Subcategory> list, ClickItemListener clickItemListener) {
        this.listAcsubcategory = list;
        this.listener = clickItemListener;
    }

    @NonNull
    @Override
    public AcSubAdapter.AcsubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AcsubViewHolder(ItemSubCategoriesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AcSubAdapter.AcsubViewHolder holder, int position) {
        holder.bind(listAcsubcategory.get(position));
    }

    @Override
    public int getItemCount() {
        return listAcsubcategory.size();
    }

    public class AcsubViewHolder extends RecyclerView.ViewHolder {
        ItemSubCategoriesBinding binding;

        public AcsubViewHolder(@NonNull ItemSubCategoriesBinding itemSubCategoriesBinding) {
            super(itemSubCategoriesBinding.getRoot());
            binding = itemSubCategoriesBinding;
        }

         void bind(Subcategory subcategory){
            binding.tvsubitem.setText(subcategory.getTitle());

            // format money
            long priceService = subcategory.getPrice();
            final String price = Utils.moneyFormat(priceService);

            binding.tvpricesub.setText(price);
            Glide.with(itemView.getContext())
                    .load(subcategory.getPhoto())
                    .transform(new CenterCrop(),new RoundedCorners(20))
                    .into(binding.imgsubitem);
            // on item clicked
            binding.cardView.setOnClickListener((v)->{
                listener.onItemClicked(subcategory);
            });
        }
    }
    // callback clickitem
    public interface ClickItemListener {
        void onItemClicked(Subcategory subcategory);
    }
}
