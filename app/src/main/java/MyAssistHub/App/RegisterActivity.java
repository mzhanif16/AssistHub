package MyAssistHub.App;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import MyAssistHub.App.api.ApiClient;
import MyAssistHub.App.api.ApiInterface;
import MyAssistHub.App.model.register.Register;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etFirstname,etLastname,etUsername,etPhonenumber,etEmail,etPassword;
    TextView tvSignIn;
    Button btnSignup;
    String firstname,lastname,username,phonenumber,email,password;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstname = findViewById(R.id.etFirstName);
        etLastname = findViewById(R.id.etLastName);
        etUsername = findViewById(R.id.etUsername);
        etPhonenumber = findViewById(R.id.etPhoneNumber);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etpassword);
        btnSignup = findViewById(R.id.btnSignUp);
        btnSignup.setOnClickListener(this);

        tvSignIn = findViewById(R.id.tvsignIn);
        tvSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvsignIn:
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
            break;
            case R.id.btnSignUp:
                firstname = etFirstname.getText().toString();
                lastname = etLastname.getText().toString();
                username = etUsername.getText().toString();
                email = etEmail.getText().toString();
                phonenumber = etPhonenumber.getText().toString();
                password = etPassword.getText().toString();
                register(firstname,lastname,username,email,phonenumber,password);
        }
    }

    private void register(String firstname, String lastname, String username, String email, String phonenumber, String password) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Register> call = apiInterface.Registerresponse(firstname,lastname,username,phonenumber,email,password);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus() ){
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}