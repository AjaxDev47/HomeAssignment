package com.example.appcjs;

import static com.example.appcjs.Constant.EMAIL;
import static com.example.appcjs.MainActivity.editor;
import static com.example.appcjs.MainActivity.sharedPreferencesRed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    TextView txtEmail;
    Button btnLogout;

    //SharedPreferences sharedPreferences, sharedPreferencesRed;
    //SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (TextView) findViewById(R.id.user_email);
        btnLogout = (Button) findViewById(R.id.btn_logout);


        sharedPreferencesRed = getSharedPreferences(Constant.SHARED_PREF, MODE_APPEND);
        String email = sharedPreferencesRed.getString("email", "");
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        txtEmail.setText(email);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("email", "");
                editor.apply();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });


    }
}