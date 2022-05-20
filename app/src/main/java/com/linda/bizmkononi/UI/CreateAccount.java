package com.linda.bizmkononi.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class CreateAccount extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.register) AppCompatButton register;
    @BindView(R.id.sign_in) TextView signIn;
    @BindView(R.id.name) EditText name;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.phone) EditText phone;
    @BindView(R.id.password) EditText password;
    @BindView(R.id.confirm) EditText confirmPassword;

    String myName;
    String myEmail;
    String phoneNumber;
    String pass;
    String confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        //Onclick Listeners
        register.setOnClickListener(this);
        signIn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == register){
            verify();
        }
        if (v == signIn){
            startActivity(new Intent(CreateAccount.this, SignIn.class));
        }
    }

    private void verify() {
        myName = name.getText().toString();
        myEmail = email.getText().toString();
        phoneNumber = String.valueOf(phone.getText());
        pass = password.getText().toString();
        confirmPass = confirmPassword.getText().toString();

        if (myName.equals("")){
            name.setError("Please enter you name");
            return;
        }
        if (myEmail.equals("")){
            email.setError("Please enter your email");
            return;

        }
        if (phoneNumber.equals("")){
            phone.setError("Please enter your phone number");
            return;

        }
        if (pass.equals("")||confirmPass.equals("")){
            password.setError("Please enter your paswword");
            return;

        }
        if (!pass.equals(confirmPass)){
            confirmPassword.setError("Please ensure you password match");
            return;

        }
        if(pass.length()<7){
            password.setError("Your password is too short");
            return;

        }


        Users users = new Users();
        users.setName(myName);
        users.setEmail(myEmail);
        users.setPhone(phoneNumber);
        users.setPassword(pass);

        Service service = Client.getClient();
        ProgressDialog pd = new ProgressDialog(CreateAccount.this);
        pd.setMessage("Please Wait...");
        pd.show();
        Call<Users> usersCall = service.createUser(users);
        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Account successfully created.Please check your email for a verification link",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CreateAccount.this, SignIn.class));
                    pd.hide();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Sorry, Please try again later",Toast.LENGTH_LONG).show();
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