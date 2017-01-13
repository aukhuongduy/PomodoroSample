package com.example.khuongduy.pomodorosample.activities;

import android.app.Notification;
import android.media.Image;
import android.nfc.Tag;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khuongduy.pomodorosample.R;
import com.example.khuongduy.pomodorosample.activities.Model.Account;

public class LoginActivity extends AppCompatActivity {

    private SearchView svSearch;
    private SeekBar sbTime;
    private RatingBar rtbPasswordStrong;
    private RadioButton rbtTomato;
    private RadioButton rbtOrange;
    private RadioButton rbtApple;
    private Spinner spLanguage;
    private ImageView imLogo;
    private EditText etUsername;
    private EditText etPassword;
    private Button btRegister;
    private Button btLogin;
    private CheckBox cbAdmin;
    private TextView tvTime;
    private TextView tvSearch;
    private ProgressBar pbDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setReference();
        setUI();
        setListener();


    }

    private void setListener() {
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
        spLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        imLogo.setImageResource(R.drawable.icon256);
                        rbtTomato.setChecked(true);
                        break;
                    case 1:
                        imLogo.setImageResource(R.drawable.orange);
                        rbtOrange.setChecked(true);
                        break;
                    case 2:
                        imLogo.setImageResource(R.drawable.apple);
                        rbtApple.setChecked(true);
                        break;
                    default:
                        imLogo.setImageResource(R.drawable.icon256);
                        break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cbAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAdmin.isChecked()) {
                    Toast.makeText(LoginActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rbtOrange.setChecked(true);
        rbtTomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spLanguage.setSelection(0);
            }
        });
        rbtOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spLanguage.setSelection(1);
            }
        });
        rbtApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spLanguage.setSelection(2);
            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                float rating = (etPassword.getText().toString().length())*5/15;
                rtbPasswordStrong.setRating(rating);

            }
        });
        sbTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvTime.setText(Integer.toString(progress));
                pbDown.setProgress(progress);
                
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tvSearch.setText(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void setReference() {
        pbDown = (ProgressBar) this.findViewById(R.id.pbHorizontal);
        svSearch = (SearchView) this.findViewById(R.id.svSearch);
        tvSearch = (TextView) this.findViewById(R.id.tvSearch);
        tvTime = (TextView) this.findViewById(R.id.tvTime);
        sbTime = (SeekBar) this.findViewById(R.id.sbTime);
        rtbPasswordStrong = (RatingBar) this.findViewById(R.id.rtbPasswordStrong);
        rtbPasswordStrong.setEnabled(false);
        rbtTomato = (RadioButton) this.findViewById(R.id.rbtTomato);
        rbtOrange = (RadioButton) this.findViewById(R.id.rbtOrange);
        rbtApple = (RadioButton) this.findViewById(R.id.rbtApple);
        cbAdmin = (CheckBox) this.findViewById(R.id.cbAdmin);
        spLanguage = (Spinner) this.findViewById(R.id.spLanguage);
        imLogo = (ImageView) this.findViewById(R.id.imLogo);
        etUsername = (EditText) this.findViewById(R.id.et_username);
        etPassword = (EditText) this.findViewById(R.id.et_password);
        btRegister = (Button) this.findViewById(R.id.bt_register);
        btLogin = (Button) this.findViewById(R.id.bt_login);
        spLanguage.post(new Runnable() {
            @Override
            public void run() {
                spLanguage.setSelection(1);
            }
        });
    }


    private void setUI() {
        imLogo.setImageResource(R.drawable.icon256);
        //1
        String[] language = {"Tomato", "Orange", "Apple"};
        //2
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, language
        );
        //3
        spLanguage.setAdapter(adapter);
    }

    private void attemptRegister() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (username.length() >= 4 && password.length() >= 6) {
            Account a = new Account(username, password);
            Account.instance.add(a);
            Toast.makeText(this, R.string.RegisterSuccessful, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.RegisterFailed, Toast.LENGTH_SHORT).show();
        }

    }

    private void attemptLogin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (Account.instance.size() == 0) {
            Toast.makeText(this, "Account is empty", Toast.LENGTH_SHORT).show();
        }
        if (!username.equals("") && !password.equals("")) {
            for (Account a : Account.instance) {
                if (username.equals(a.getUsername()) && password.equals(a.getPassword())) {
                    if (!cbAdmin.isChecked()) {
                        Toast.makeText(this, R.string.LoginSuccessful, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Login Successful with Admin acount", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, R.string.LoginFailed, Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, "username or password is blank", Toast.LENGTH_SHORT).show();
        }
    }
}
