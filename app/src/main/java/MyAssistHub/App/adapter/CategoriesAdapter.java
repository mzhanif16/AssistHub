package MyAssistHub.App.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import MyAssistHub.App.R;
import MyAssistHub.App.model.Categories;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {
    Context context;
    private ArrayList<Categories> listCategories;
    private OnItemClickcallback onItemClickCallback;

    public CategoriesAdapter(ArrayList<Categories> list){
        this.listCategories = list;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories,parent,false);
        return new CategoriesViewHolder(view);
    }
    public void setOnItemClickCallback(OnItemClickcallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoriesViewHolder holder, int position) {
        Categories categories = listCategories.get(position);
        Glide.with(holder.itemView.getContext())
                .load(categories.getImage())
                .apply(new RequestOptions().override(55,55))
                .into(holder.imgcategories);
        holder.tvcategories.setText(categories.getCategories());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listCategories.get(holder.getAdapterPosition()));
                if(listCategories == null){

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCategories.size();
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder{
        ImageView imgcategories;
        TextView tvcategories;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            imgcategories = itemView.findViewById(R.id.ivcategories);
            tvcategories = itemView.findViewById(R.id.tvcategories);
        }
    }
    public interface OnItemClickcallback {
        void onItemClicked(Categories data);
    }
}
