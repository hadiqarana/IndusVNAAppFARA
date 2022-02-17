package com.example.indusvnaapp.SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.indusvnaapp.EditTextActivity;
import com.example.indusvnaapp.Login.LoginActivity;
import com.example.indusvnaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
   //firebase auth
   FirebaseAuth mAuth;
   String email;
   String password;

    private EditText enter_email_edittext,enter_username_edittext,password_edittext,confirmpassword_edittext,phone_edittext,address_edittext;
    private Button SignUp;
    private TextView SignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //views ids
        enter_email_edittext=findViewById(R.id.enter_email);
        enter_username_edittext=findViewById(R.id.username);
        password_edittext=findViewById(R.id.password_edittext);
        confirmpassword_edittext=findViewById(R.id.confirmpassword_edittext);
        phone_edittext=findViewById(R.id.phone_edittext);
        address_edittext=findViewById(R.id.address_edittext);
        SignUp=findViewById(R.id.signUp_button);
        SignIn=findViewById(R.id.SignIn_button);

        //auth instatnce
        mAuth = FirebaseAuth.getInstance();

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

    }
    private boolean isEmpty(EditText editText) {
        boolean isEmptyResult = false;
        if (editText.getText().length() == 0) {
            isEmptyResult = true;
        }
        return isEmptyResult;
    }
    private void createUser(){

        email = enter_email_edittext.getText().toString();
        password = password_edittext.getText().toString();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(SignUpActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, EditTextActivity.class));
                    }else{
                        Toast.makeText(SignUpActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


