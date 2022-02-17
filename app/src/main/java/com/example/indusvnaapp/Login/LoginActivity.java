package com.example.indusvnaapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.indusvnaapp.EditTextActivity;
import com.example.indusvnaapp.R;
import com.example.indusvnaapp.SignUp.SignUpActivity;

public class LoginActivity extends AppCompatActivity {
private Button sign_in_button;
TextView signu_up_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        sign_in_button=findViewById(R.id.signUp_button);
        signu_up_button=findViewById(R.id.SignIn_button);

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this, EditTextActivity.class);
                startActivity(i);
            }
        });
        signu_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i=new Intent(LoginActivity.this, SignUpActivity.class);
               // startActivity(i);
            }
        });


    }

}