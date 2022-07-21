package MyAssistHub.App.ui.profile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import MyAssistHub.App.SessionManager;
import MyAssistHub.App.api.ApiClient;
import MyAssistHub.App.api.ApiInterface;
import MyAssistHub.App.databinding.FragmentProfileBinding;
import MyAssistHub.App.model.BaseRespone;
import MyAssistHub.App.model.user.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private SessionManager sessionManager;
    private FragmentProfileBinding binding;
    private ProgressDialog progressDialog;
    private ApiInterface apiInterface;

    private String userId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(binding!=null){
            //method update profile
            binding.cardView.setOnClickListener((v)-> updateProfile());
            sessionManager = new SessionManager(getActivity());
            userId = sessionManager.getUserId();
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            //get detail profile
            getProfile(userId);

        }
    }

    //mengambil data profile
    private void getProfile(final String userId) {

        if(progressDialog == null ){
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();

        Call<BaseRespone<UserResponse>> call = apiInterface.ProfilResponse(userId);
        call.enqueue(new Callback<BaseRespone<UserResponse>>() {
            @Override
            public void onResponse(Call<BaseRespone<UserResponse>> call, Response<BaseRespone<UserResponse>> response) {
                UserResponse userResponse = response.body().getData();
                if(userResponse != null && response.isSuccessful()){
                    binding.tvuser1.setText(userResponse.getUsername());
                    binding.PhoneNumber.setText(userResponse.getPhoneNumber());
                    binding.Email.setText(userResponse.getEmail());
                    binding.Gender.setText(userResponse.getGender());
                    binding.Dateofbirth.setText(userResponse.getBirthDate());
                    binding.Address.setText(userResponse.getAddress());
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<BaseRespone<UserResponse>> call, Throwable t) {
                try {
                    progressDialog.dismiss();
                    Snackbar.make(binding.getRoot(), "Try again!",
                            Snackbar.LENGTH_LONG).setAction("RETRY", (v)-> getProfile(userId)).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
    //update data profile pada database
    private void updateProfile() {
        final String email = binding.Email.getText().toString();
        final String phone = binding.PhoneNumber.getText().toString();
        final String gender = binding.Gender.getText().toString();
        final String birthDate = binding.Dateofbirth.getText().toString();
        final String address = binding.Address.getText().toString();

        Log.e("ProfileFragment ", "email: "+email
        +"\nphone "+ phone);
        if(progressDialog == null ){
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();

        Call<BaseRespone> callUpdate = apiInterface.UpdateResponse(userId,phone,email,gender,birthDate,address);
        callUpdate.enqueue(new Callback<BaseRespone>() {
            @Override
            public void onResponse(Call<BaseRespone> call, Response<BaseRespone> response) {
                progressDialog.dismiss();
                if(response.body() != null && response.isSuccessful()){
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Snackbar.make(binding.getRoot(), "Try again!",
                            Snackbar.LENGTH_LONG).setAction("RETRY", (v)-> updateProfile()).show();
                }
            }
            @Override
            public void onFailure(Call<BaseRespone> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}