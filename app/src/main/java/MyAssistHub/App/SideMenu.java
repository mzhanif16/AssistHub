package MyAssistHub.App;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

import MyAssistHub.App.databinding.ActivitySideMenuBinding;
import MyAssistHub.App.model.Categories;

public class SideMenu extends AppCompatActivity {
    private ArrayList<Categories> list = new ArrayList<>();
    private AppBarConfiguration mAppBarConfiguration;
    private ActivitySideMenuBinding binding;
    private SessionManager sessionManager;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(SideMenu.this);
        if(!sessionManager.isLoggenin()){
            moveToLogin();
        }
        binding = ActivitySideMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarSideMenu.toolbar1);
        //DrawerLayout drawer = binding.drawerLayout;
        //NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_profile, R.id.nav_home, R.id.nav_booking, R.id.nav_offer)
                .setOpenableLayout(binding.drawerLayout)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_side_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


    }
    //navigate to offer fragment
    public void setSelectedNavOffer(){
        navController.navigate(R.id.nav_offer);
    }

    private void moveToLogin(){
        Intent intent = new Intent(SideMenu.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.side_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionlogout:
                sessionManager.logoutSession();
                Movetologin();
        }
        return super.onOptionsItemSelected(item);
    }

    private void Movetologin(){
        Intent intent = new Intent(SideMenu.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_side_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}