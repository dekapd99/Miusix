package com.deka_32864.miusix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button LoginUser;

    String correct_username = "uasmobile";
    String correct_password = "uasmobilegenap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
        Username = (EditText) findViewById(R.id.etUsername);
        Password = (EditText) findViewById(R.id.etPassword);
        LoginUser = (Button) findViewById(R.id.btnLoginUser);

        //validate username & password
        LoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(Username.getText().toString()) || TextUtils.isEmpty(Password.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Username/Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (Username.getText().toString().equals(correct_username) && Password.getText().toString().equals(correct_password)){
                    //                    Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                } else{
                    Toast.makeText(LoginActivity.this, "Salah Username/Password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}