package MyAssistHub.App;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import MyAssistHub.App.adapter.AcSubAdapter;
import MyAssistHub.App.databinding.ActivitySubCategoryBinding;
import MyAssistHub.App.helpers.DummyHelpers;
import MyAssistHub.App.model.Subcategory;

public class SubCategoryActivity extends AppCompatActivity {
    private ActivitySubCategoryBinding binding;
    private ArrayList<Subcategory> subcategories;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        categoryName = getIntent().getStringExtra("category");
        binding.tvTitle.setText(categoryName);
        initLayout(categoryName);
    }
    private void initLayout(String category){
        subcategories = DummyHelpers.getSubCategory(category);
        AcSubAdapter acSubAdapter = new AcSubAdapter(subcategories,(subcategory)->{
            Intent intent = new Intent(getApplicationContext(), ServiceDetailActivity.class);
            intent.putExtra("category",categoryName);
            intent.putExtra("subcategory",subcategory);
            startActivity(intent);
        });
        binding.rvsubcategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        binding.rvsubcategories.setAdapter(acSubAdapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}