package com.linda.bizmkononi.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.linda.bizmkononi.Models.Users;
import com.linda.bizmkononi.Network.Client;
import com.linda.bizmkononi.Network.Service;
import com.linda.bizmkononi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.signIn) Button signIn;
    @BindView(R.id.sign_up) TextView signUp;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.password) EditText pass;

    String emailAddress;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == signIn){
            login();
        }
        if (v == signUp){
            startActivity(new Intent(SignIn.this,CreateAccount.class));

        }
    }

    private void login() {
        emailAddress = email.getText().toString();
        password =pass.getText().toString();
        if (emailAddress.equals("")){
            email.setError("Please enter your email address!");
            return;
        }
        if (password.equals("")){
            pass.setError("Please enter your password");
            return;
        }

        Users users = new Users();

        users.setEmail(emailAddress);
        users.setPassword(password);

        Service service = Client.getClient();
        ProgressDialog pd = new ProgressDialog(SignIn.this);
        pd.setMessage("Please Wait...");
        pd.show();
        Call<Users> usersCall = service.loginUser(users);
        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignIn.this, MainActivity.class));
                    pd.hide();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignIn.this, MainActivity.class));
                    pd.hide();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG).show();
                pd.hide();
            }
        });
    }
}