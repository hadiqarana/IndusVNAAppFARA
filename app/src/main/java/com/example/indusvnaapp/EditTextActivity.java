package com.example.indusvnaapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.indusvnaapp.Login.SignInFlow;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EditTextActivity extends AppCompatActivity {
    private OkHttpClient okHttpClient;
    //edit texts
    private EditText MR_number_edittext, Head_size_edittext, length_edittext, Wt_edittext, ges_age_editext, hr_edittext, rr_edittext, temp_edittext,age_days_edittext;
    //next button
    private Button Next;
    CoordinatorLayout coordinatorLayout;

    //username
    private String mUsername;
    public static final String ANONYMOUS = "anonymous";
    public static final int RC_SIGN_IN = 1;
    //firebase auth
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    //edittext value variables
    private String Mr_number, HeadSize, Length, Wt, ges_age, HR, RR, Temp,Age_days;
    private String gender="";
    private String id;
    Bundle bundle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        //set username
      //  mUsername = ANONYMOUS;
      //  mFirebaseAuth = FirebaseAuth.getInstance();
        //button next id
        Next=findViewById(R.id.NextButton);
        MR_number_edittext = findViewById(R.id.MR_number);
        Head_size_edittext = findViewById(R.id.Head_size_edittext);
        length_edittext = findViewById(R.id.length_edittext);
        Wt_edittext = findViewById(R.id.weight_edittext);
        ges_age_editext = findViewById(R.id.ges_age_edittext);
        age_days_edittext=findViewById(R.id.age_edittext);
        hr_edittext = findViewById(R.id.hr_min_edittext);
        rr_edittext = findViewById(R.id.RR_br_min_editttext);
        temp_edittext = findViewById(R.id.temp_edittext);
        RadioGroup radioGroup = findViewById(R.id.radioSex);

        //radio group functionality when gender is selected
        bundle = new Bundle();
        id=UUID.randomUUID().toString();

        RadioButton genderBtn = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        gender = genderBtn.getText().toString();

        //okhttp
        okHttpClient = new OkHttpClient();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.radioMale:
                        gender = "Male";
                        break;
                    case R.id.radioFemale:
                        gender = "Female";
                        break;
                }
            }
        });


        //call input validation method
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Input_Validation();
                Mr_number=MR_number_edittext.getText().toString().trim();
                RequestBody formbody
                        = new FormBody.Builder()
                        .add("mr_number",Mr_number )
                        //.add("saa",dummyText)
                        .build();

                // while building request
                // we give our form
                // as a parameter to post()
                Request request = new Request.Builder().url("http://10.0.0.47:5000/debug")
                        .post(formbody)
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(
                            @NotNull Call call,
                            @NotNull IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "server down", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.body().string().equals("received")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "data received", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
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
    public void Pass_Data(){
        //get edit text values
        Mr_number=MR_number_edittext.getText().toString().trim();
        HeadSize=Head_size_edittext.getText().toString().trim();
        Length=length_edittext.getText().toString().trim();
        Wt=Wt_edittext.getText().toString().trim();
        HR=hr_edittext.getText().toString().trim();
        ges_age=ges_age_editext.getText().toString().trim();
        Age_days=age_days_edittext.getText().toString().trim();
        RR=rr_edittext.getText().toString().trim();
        Temp=temp_edittext.getText().toString().trim();
        bundle.putString("id",id);
        bundle.putString("MR_Number",Mr_number);
        bundle.putString("Head_size",HeadSize);
        bundle.putString("Length",Length);
        bundle.putString("Wt",Wt);
        bundle.putString("HR",HR);
        bundle.putString("RR",RR);
        bundle.putString("age",ges_age);
        bundle.putString("age_days",Age_days);
        bundle.putString("Temp",Temp);
        bundle.putString("gender",gender);






        Intent i = new Intent(EditTextActivity.this, MainActivity.class);

        i.putExtras(bundle);
        startActivity(i);

    }
    public void Input_Validation(){

                if(isEmpty(MR_number_edittext))
                {
                    //it gives user to hint
                    MR_number_edittext.setError("required");
                    MR_number_edittext.requestFocus();

                }
                else if(isEmpty(Head_size_edittext)){
                    Head_size_edittext.setError("required");
                    Head_size_edittext.requestFocus();
                }
                else if(isEmpty(length_edittext)){
                    length_edittext.setError("required");
                    length_edittext.requestFocus();
                }
                else if(isEmpty(Wt_edittext)){
                    Wt_edittext.setError("required");
                    Wt_edittext.requestFocus();
                }
                else if(isEmpty(hr_edittext)){
                    hr_edittext.setError("required");
                    hr_edittext.requestFocus();


                }
                else if(isEmpty(rr_edittext)){
                    rr_edittext.setError("required");
                    rr_edittext.requestFocus();
                }
                else if(isEmpty(temp_edittext)){
                    temp_edittext.setError("required");
                  temp_edittext.requestFocus();
                }
                else if(isEmpty(ges_age_editext)){
                    ges_age_editext.setError("required");
                    ges_age_editext.requestFocus();
                }
                else if(isEmpty(age_days_edittext)){
                    age_days_edittext.setError("required");
                    age_days_edittext.requestFocus();
                }

                else {
                   Pass_Data();
                }
            }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                //AuthUI.getInstance().signOut(this);
                signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Intent i=new Intent(EditTextActivity.this, SignInFlow.class);
                        startActivity(i);
                    }
                });
        // [END auth_fui_signout]
    }

    }
