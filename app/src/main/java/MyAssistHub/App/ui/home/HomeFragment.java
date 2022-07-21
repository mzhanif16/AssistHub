package MyAssistHub.App.ui.home;

import android.content.Intent;
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

import MyAssistHub.App.SessionManager;
import MyAssistHub.App.SideMenu;
import MyAssistHub.App.SubCategoryActivity;
import MyAssistHub.App.adapter.AdapterOffer;
import MyAssistHub.App.adapter.CategoriesAdapter;
import MyAssistHub.App.databinding.FragmentHomeBinding;
import MyAssistHub.App.helpers.DummyHelpers;
import MyAssistHub.App.model.Categories;


public class HomeFragment extends Fragment {
    static final String EXTRA_CATEGORY = "category";
    private SessionManager sessionManager;
    private FragmentHomeBinding binding;
    private ArrayList<Categories> categories;
    private ArrayList<String> offers;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(binding!=null){
            sessionManager = new SessionManager(getActivity());
            binding.tvUsername.setText(sessionManager.getUsername());
            setupDummyCategories();
            setupDummyOffers();
            binding.tvSeeAll.setOnClickListener((v)->{
                ((SideMenu)getActivity()).setSelectedNavOffer();
            });
        }
    }

    private void setupDummyCategories() {
        categories = DummyHelpers.getCategories();
        CategoriesAdapter listcategoriesadapter = new CategoriesAdapter(categories);
        binding.rvcategories.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        binding.rvcategories.setAdapter(listcategoriesadapter);
        listcategoriesadapter.setOnItemClickCallback((category)-> {
            Intent intent = new Intent(getActivity(),SubCategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, category.getCategories());
            startActivity(intent);
        });
    }
    private void setupDummyOffers(){
        offers = new ArrayList<>();
        final String[] discounts = {"Discount 25% Today","Discount 50% Friday","Discount 10% Monday","Discount 70% Thursday"};
        for(int i=0; i<discounts.length; i++){
            offers.add(discounts[i]);
        }
        binding.rcOffers.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        binding.rcOffers.setAdapter(new AdapterOffer(offers,(val)->{
            Toast.makeText(getActivity(), val, Toast.LENGTH_SHORT).show();
        }));
    }
}