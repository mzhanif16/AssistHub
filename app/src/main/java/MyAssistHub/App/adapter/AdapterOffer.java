package MyAssistHub.App.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import MyAssistHub.App.databinding.RowItemOfferBinding;
import MyAssistHub.App.databinding.RowItemOfferVerticalBinding;


public class AdapterOffer extends RecyclerView.Adapter {
    private ArrayList<String> offersList;
    private ClickItemListener clickItemListener;
    private static final int TYPE_ITEM_HORIZONTAL = 0;
    private static final int TYPE_ITEM_VERTICAL = 1;
    private boolean isVertical = false;

    public AdapterOffer(ArrayList<String> list, ClickItemListener listener) {
        this.offersList = list;
        this.clickItemListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View itemView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
        if (viewType == TYPE_ITEM_HORIZONTAL) {
            //itemView = View.inflate(parent.getContext(), R.layout.row_comment_replay_item, null);
            return new OfferViewHolder(RowItemOfferBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        } else {
            return new VerticalViewHolder(RowItemOfferVerticalBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AdapterOffer.OfferViewHolder) {
            OfferViewHolder itemholder = (AdapterOffer.OfferViewHolder) holder;
            itemholder.bind(offersList.get(position));
        } else {
            VerticalViewHolder itemholder = (AdapterOffer.VerticalViewHolder) holder;
            itemholder.bind(offersList.get(position));
        }
    }
    @Override
    public int getItemViewType(int position) {
        if ( isVertical )
            return TYPE_ITEM_VERTICAL;
        return TYPE_ITEM_HORIZONTAL;
    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    //set view holder VerticalViewHolder or OfferViewHolder
    public void setIsVertical(boolean bool){
        isVertical = bool;
    }

    // holder view fullwidth for horizontal RecyclerView
    public class OfferViewHolder extends RecyclerView.ViewHolder {
        RowItemOfferBinding binding;

        public OfferViewHolder(@NonNull RowItemOfferBinding rowItemOfferBinding) {
            super(rowItemOfferBinding.getRoot());
            binding = rowItemOfferBinding;
        }

        void bind(String titleOffer) {
            binding.titleOffer.setText(titleOffer);
            itemView.setOnClickListener((v) -> clickItemListener.onItemClicked(titleOffer));
        }
    }

    // holder view fullwidth for vertical RecyclerView
    public class VerticalViewHolder extends RecyclerView.ViewHolder {
        RowItemOfferVerticalBinding binding;

        public VerticalViewHolder(@NonNull RowItemOfferVerticalBinding rowItemOfferVerticalBinding) {
            super(rowItemOfferVerticalBinding.getRoot());
            binding = rowItemOfferVerticalBinding;
        }

        void bind(String titleOffer) {
            binding.titleOffer.setText(titleOffer);
            itemView.setOnClickListener((v) -> clickItemListener.onItemClicked(titleOffer));
        }
    }

    // callback clickitem
    public interface ClickItemListener {
        void onItemClicked(String titleOffers);
    }
}
