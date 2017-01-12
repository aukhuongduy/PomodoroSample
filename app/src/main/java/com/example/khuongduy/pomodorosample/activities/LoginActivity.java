package com.example.khuongduy.pomodorosample.activities;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khuongduy.pomodorosample.R;
import com.example.khuongduy.pomodorosample.activities.Model.Account;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btRegister;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) this.findViewById(R.id.et_username);
        etPassword = (EditText) this.findViewById(R.id.et_password);
        btRegister = (Button) this.findViewById(R.id.bt_register);
        btLogin = (Button) this.findViewById(R.id.bt_login);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();
            }
        });
    }

    private void attemptRegister() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if(username.length()>=4 && password.length()>=6){
            Account a = new Account(username,password);
            Account.instance.add(a);
            Toast.makeText(this, R.string.RegisterSuccessful, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, R.string.RegisterFailed, Toast.LENGTH_SHORT).show();
        }

    }

    private void attemptLogin() {
        String username =  etUsername.getText().toString();
        String password = etPassword.getText().toString();
        for (Account a: Account.instance) {
            if(username.equals(a.getUsername())&& password.equals(a.getPassword())){
                Toast.makeText(this, R.string.LoginSuccessful, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, R.string.LoginFailed, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
