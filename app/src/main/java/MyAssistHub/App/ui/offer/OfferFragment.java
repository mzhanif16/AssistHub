package MyAssistHub.App.ui.offer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import MyAssistHub.App.adapter.AdapterOffer;
import MyAssistHub.App.databinding.FragmentOfferBinding;


public class OfferFragment extends Fragment {
    private ArrayList<String> offers;
    private FragmentOfferBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOfferBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(binding!=null){
            offers = new ArrayList<>();
            final String[] discounts = {"Discount 25% Today","Discount 50% Friday","Discount 10% Monday","Discount 70% Thursday"};
            for(int i=0; i<discounts.length; i++){
                offers.add(discounts[i]);
            }
            binding.rvOffers.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
            AdapterOffer adapter = new AdapterOffer(offers,(val)->{
                Toast.makeText(getActivity(), val, Toast.LENGTH_SHORT).show();
            });
            adapter.setIsVertical(true);
            binding.rvOffers.setAdapter(adapter);
        }
    }
}