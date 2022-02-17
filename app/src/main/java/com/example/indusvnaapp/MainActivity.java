package com.example.indusvnaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.Request;
import okhttp3.RequestBody;

//import com.example.indusvnaapp.DataModel.Data;
import com.example.indusvnaapp.Login.SignInFlow;
import com.example.indusvnaapp.Requests.RetrofitClient;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import okhttp3.FormBody;
import retrofit2.Call;


import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private OkHttpClient okHttpClient;
    //ok http client


    private String id;
    private boolean check = true;
    private String Head_shape;
    private String Head_size;
    private String Swelling;
    private String Eyes_right;
    private String Eyes_left;
    private String Ears_right;
    private String Ears_left;
    private String Nose;
    private String Mouth_lips;
    private String Mouth_chin;
    private String facial_apeearance;
    private String Features_relatedTo;
    private String Neck;
    private String Chest;
    private String Right_Uper_limb;
    private String Left_upper_limb;
    private String Right_hand;
    private String Left_hand;
    private String Abdomen;
    private String Umbilicus;
    private String Penis;
    private String Scrotum;
    private String Female_Genitalia;
    private String Legs_position;
    private String Right_lower_limb;
    private String Left_lower_limb;
    private String Right_foot;
    private String Left_foot;
    private String Back;
    private String Color;
    private String Edit_by;
    private String Time;
    private String Age_days;
    //spinners
    private Spinner head_shape_spinner;
    private Spinner Head_size_spinner;
    private Spinner Swelling_spinner;
    private Spinner Eyes_right_spinner;
    private Spinner Eyes_left_spinner;
    private Spinner Ears_right_spinner;
    private Spinner Ears_left_spinner;
    private Spinner Nose_spinner;
    private Spinner Mouth_lips_spinner;
    private Spinner Mouth_chin_spinner;
    private Spinner facial_apeearance_spinner;
    private Spinner Features_relatedTo_spinner;
    private Spinner Neck_spinner;
    private Spinner Chest_spinner;
    private Spinner Right_Uper_limb_spinner;
    private Spinner Left_upper_limb_spinner;
    private Spinner Right_hand_spinner;
    private Spinner Left_hand_spinner;
    private Spinner Abdomen_spinner;
    private Spinner Umbilicus_spinner;
    private Spinner Penis_spinner;
    private Spinner Scrotum_spinner;
    private Spinner Female_Genitalia_spinner;
    private Spinner Legs_position_spinner;
    private Spinner Right_lower_limb_spinner;
    private Spinner Left_lower_limb_spinner;
    private Spinner Right_foot_spinner;
    private Spinner Left_foot_spinner;
    private Spinner Back_spinner;
    private Spinner Color_spinner;
    //edit texts
    private EditText MR_number_edittext, Head_size_edittext, length_edittext, Wt_edittext, ges_age_editext, hr_edittext, rr_edittext, temp_edittext;

    //edittext value variables
    private String Mr_number, Head_Size, Length, Wt, ges_age, HR, RR, Temp;


    private String gender;

    //next button
    private Button Next;

    //firebase references
    //   private FirebaseDatabase firebaseDatabase;
    //private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        okHttpClient = new OkHttpClient();

        //  firebaseDatabase = FirebaseDatabase.getInstance();
        //  databaseReference = firebaseDatabase.getReference().child("data");


        //code to request body

/// ok http client

        //next button
        Next = findViewById(R.id.next);


        //gson format of data
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        ///retrofit library
        //trofit retrofit = new Retrofit.Builder()
        //      .baseUrl("67.205.105.123:5000")
        //     .addConverterFactory(GsonConverterFactory.create(gson))
        //     .build();

        //edit texts ids
//calling api from
        //  Api apiService = retrofit.create(Api.class);
        // Call<Data> call = apiService.createuser(data);
       /* call.enqueue(new Callback<Data>() {
                         @Override
                         public void onResponse(Call<Data> call, Response<Data> response) {

                         }

                         @Override
                         public void onFailure(Call<Data> call, Throwable t) {

                         }
                         */


        //spinners ids
        head_shape_spinner = findViewById(R.id.head_shape_spinner);
        Head_size_spinner = findViewById(R.id.head_size_spinner);
        Swelling_spinner = findViewById(R.id.Head_swelling);
        Eyes_right_spinner = findViewById(R.id.eyes_right_spinner);
        Eyes_left_spinner = findViewById(R.id.eyes_left_spinner);
        Ears_right_spinner = findViewById(R.id.ears_right_spinner);
        Ears_left_spinner = findViewById(R.id.ears_left_spinner);
        Nose_spinner = findViewById(R.id.nose_spinner);
        Mouth_lips_spinner = findViewById(R.id.mouth_lips_spinner);
        Mouth_chin_spinner = findViewById(R.id.mouth_chin_spinner);
        facial_apeearance_spinner = findViewById(R.id.Facial_appearance_spinner);
        Features_relatedTo_spinner = findViewById(R.id.feature_related_to_spinner);
        Neck_spinner = findViewById(R.id.neck_spinner);
        Chest_spinner = findViewById(R.id.chest_spinner);
        Right_Uper_limb_spinner = findViewById(R.id.right_upper_limb_spinner);
        Left_upper_limb_spinner = findViewById(R.id.left_upper_limb_spinner);
        Right_hand_spinner = findViewById(R.id.right_hand_spinner);
        Left_hand_spinner = findViewById(R.id.left_hand_spinner);
        Abdomen_spinner = findViewById(R.id.abdomen_spinner);
        Umbilicus_spinner = findViewById(R.id.umbilicus_spinner);
        Penis_spinner = findViewById(R.id.Penis_spinner);
        Scrotum_spinner = findViewById(R.id.scrotum_spinner);
        Female_Genitalia_spinner = findViewById(R.id.female_Genitalia_spinner);
        Legs_position_spinner = findViewById(R.id.legs_position_spinner);
        Right_lower_limb_spinner = findViewById(R.id.right_lower_limb_spinner);
        Left_lower_limb_spinner = findViewById(R.id.left_lower_limb_spinner);
        Right_foot_spinner = findViewById(R.id.right_foot_spinner);
        Left_foot_spinner = findViewById(R.id.left_foot_spinner);
        Back_spinner = findViewById(R.id.back_spinner);
        Color_spinner = findViewById(R.id.color_spinner);

//setting item select listeners on spinners

        head_shape_spinner.setOnItemSelectedListener(MainActivity.this);
        Head_size_spinner.setOnItemSelectedListener(MainActivity.this);
        Swelling_spinner.setOnItemSelectedListener(MainActivity.this);
        Eyes_right_spinner.setOnItemSelectedListener(MainActivity.this);
        Eyes_left_spinner.setOnItemSelectedListener(MainActivity.this);
        Ears_right_spinner.setOnItemSelectedListener(MainActivity.this);
        Ears_left_spinner.setOnItemSelectedListener(MainActivity.this);
        Nose_spinner.setOnItemSelectedListener(MainActivity.this);
        Scrotum_spinner.setOnItemSelectedListener(MainActivity.this);
        Mouth_lips_spinner.setOnItemSelectedListener(MainActivity.this);
        Mouth_chin_spinner.setOnItemSelectedListener(MainActivity.this);
        facial_apeearance_spinner.setOnItemSelectedListener(MainActivity.this);
        Features_relatedTo_spinner.setOnItemSelectedListener(MainActivity.this);
        Neck_spinner.setOnItemSelectedListener(MainActivity.this);
        Chest_spinner.setOnItemSelectedListener(MainActivity.this);
        Right_Uper_limb_spinner.setOnItemSelectedListener(MainActivity.this);
        Left_upper_limb_spinner.setOnItemSelectedListener(MainActivity.this);
        Right_hand_spinner.setOnItemSelectedListener(MainActivity.this);
        Left_hand_spinner.setOnItemSelectedListener(MainActivity.this);
        Abdomen_spinner.setOnItemSelectedListener(MainActivity.this);
        Umbilicus_spinner.setOnItemSelectedListener(MainActivity.this);
        Penis_spinner.setOnItemSelectedListener(MainActivity.this);
        Scrotum_spinner.setOnItemSelectedListener(MainActivity.this);
        Female_Genitalia_spinner.setOnItemSelectedListener(MainActivity.this);
        Legs_position_spinner.setOnItemSelectedListener(MainActivity.this);
        Right_lower_limb_spinner.setOnItemSelectedListener(MainActivity.this);
        Left_lower_limb_spinner.setOnItemSelectedListener(MainActivity.this);
        Right_foot_spinner.setOnItemSelectedListener(MainActivity.this);
        Left_foot_spinner.setOnItemSelectedListener(MainActivity.this);
        Back_spinner.setOnItemSelectedListener(MainActivity.this);


        Color_spinner.setPrompt("color");
        Color_spinner.setOnItemSelectedListener(MainActivity.this);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Upload_data();
            }
        });

        ///set time

        Date currentTime = Calendar.getInstance().getTime();
        Time = String.valueOf(currentTime);

        //get current user
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //Edit_by= user.getEmail();

    }

    //head_shape_spinner.setPrompt("Head Shape");


    // ArrayAdapter ad =  ArrayAdapter.createFromResource(this, R.array.Shape, android.R.layout.simple_spinner_item);
    //  ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    //head_shape_spinner.setAdapter(ad);


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // head_shape_spinner.getItemAtPosition(head_shape_spinner.getSelectedItemPosition());
        //Head_shape=adapterView.getSelectedItem().toString();
        // if(adapterView.getSelectedItemPosition()==0){
        //check=false;


        //  head_shape_spinner.setSelection(1,false);
        //  head_shape_spinner.setTop(0);

        // }


        // int id = adapterView.getId();
        //  switch (id) {
        //     case R.id.head_shape_spinner:
        //        if(i==0){

        //           Log.e("debud", String.valueOf(id));
        //           head_shape_spinner.setSelection(0,false);
        //           check=true;
        //           return;
        //      }

        Head_shape = (String) head_shape_spinner.getItemAtPosition(head_shape_spinner.getSelectedItemPosition());
        Head_size = (String) Head_size_spinner.getItemAtPosition(Head_size_spinner.getSelectedItemPosition());
        Swelling = (String) Swelling_spinner.getItemAtPosition(Swelling_spinner.getSelectedItemPosition());
        Eyes_right = (String) Eyes_right_spinner.getItemAtPosition(Eyes_right_spinner.getSelectedItemPosition());
        Eyes_left = (String) Eyes_left_spinner.getItemAtPosition(Eyes_left_spinner.getSelectedItemPosition());
        Ears_right = (String) Ears_right_spinner.getItemAtPosition(Ears_right_spinner.getSelectedItemPosition());
        Ears_left = (String) Ears_left_spinner.getItemAtPosition(Ears_left_spinner.getSelectedItemPosition());
        Nose = (String) Nose_spinner.getItemAtPosition(Nose_spinner.getSelectedItemPosition());
        Mouth_lips = (String) Mouth_lips_spinner.getItemAtPosition(Mouth_lips_spinner.getSelectedItemPosition());
        Mouth_chin = (String) Mouth_chin_spinner.getItemAtPosition(Mouth_chin_spinner.getSelectedItemPosition());
        facial_apeearance = (String) facial_apeearance_spinner.getItemAtPosition(facial_apeearance_spinner.getSelectedItemPosition());
        Features_relatedTo = (String) Features_relatedTo_spinner.getItemAtPosition(Features_relatedTo_spinner.getSelectedItemPosition());
        Neck = (String) Neck_spinner.getItemAtPosition(Neck_spinner.getSelectedItemPosition());
        Chest = (String) Chest_spinner.getItemAtPosition(Chest_spinner.getSelectedItemPosition());
        Right_Uper_limb = (String) Right_Uper_limb_spinner.getItemAtPosition(Right_Uper_limb_spinner.getSelectedItemPosition());
        Left_upper_limb = (String) Left_upper_limb_spinner.getItemAtPosition(Left_upper_limb_spinner.getSelectedItemPosition());
        Right_hand = (String) Right_hand_spinner.getItemAtPosition(Right_hand_spinner.getSelectedItemPosition());
        Left_hand = (String) Left_hand_spinner.getItemAtPosition(Left_hand_spinner.getSelectedItemPosition());
        Abdomen = (String) Abdomen_spinner.getItemAtPosition(Abdomen_spinner.getSelectedItemPosition());
        Umbilicus = (String) Umbilicus_spinner.getItemAtPosition(Umbilicus_spinner.getSelectedItemPosition());
        Penis = (String) Penis_spinner.getItemAtPosition(Penis_spinner.getSelectedItemPosition());
        Scrotum = (String) Scrotum_spinner.getItemAtPosition(Scrotum_spinner.getSelectedItemPosition());

        Female_Genitalia = (String) Female_Genitalia_spinner.getItemAtPosition(Female_Genitalia_spinner.getSelectedItemPosition());
        Legs_position = (String) Legs_position_spinner.getItemAtPosition(Legs_position_spinner.getSelectedItemPosition());
        Right_lower_limb = (String) Right_lower_limb_spinner.getItemAtPosition(Right_lower_limb_spinner.getSelectedItemPosition());
        Left_lower_limb = (String) Left_lower_limb_spinner.getItemAtPosition(Left_lower_limb_spinner.getSelectedItemPosition());
        Right_foot = (String) Right_foot_spinner.getItemAtPosition(Right_foot_spinner.getSelectedItemPosition());
        Left_foot = (String) Left_foot_spinner.getItemAtPosition(Left_foot_spinner.getSelectedItemPosition());
        Back = (String) Back_spinner.getItemAtPosition(Back_spinner.getSelectedItemPosition());
        Color = (String) Color_spinner.getItemAtPosition(Color_spinner.getSelectedItemPosition());


    }

    //Log.e("headshape",Head_shape);
    //databaseReference.push().setValue(Head_shape);
    // databaseReference.push().setValue(Head_size);


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        //changes the selected item text to this

    }

    private void Upload_data() {
        //String id = UUID.randomUUID().toString();
        //getting id data from bundle
        Bundle bundle1 = getIntent().getExtras();
        id = bundle1.getString("id");
        Log.e("id", id);
        Mr_number = bundle1.getString("MR_Number");
        Log.e("mr number", Mr_number);
        Head_Size = bundle1.getString("Head_size");
        Length = bundle1.getString("Length");
        Wt = bundle1.getString("Wt");
        HR = bundle1.getString("HR");
        gender = bundle1.getString("gender");
        ges_age = bundle1.getString("age");
        RR = bundle1.getString("RR");
        Temp = bundle1.getString("Temp");
        Age_days = bundle1.getString("age_days");

        //spinners validation
        if (head_shape_spinner.getSelectedItem().toString().equals("Head Shape")) {

            // Toasty.error(MainActivity.this, "select head shape", Toast.LENGTH_SHORT, true).show();

            ((TextView) head_shape_spinner.getSelectedView()).setError("Error message");
            head_shape_spinner.requestFocus();
            head_shape_spinner.setFocusableInTouchMode(true);
            head_shape_spinner.setFocusable(true);
            Toast.makeText(MainActivity.this, "please select Head Shape", Toast.LENGTH_LONG).show();
        } else if (Head_size_spinner.getSelectedItem().toString().equals("Head Size")) {
            ((TextView) Head_size_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Head Size", Toast.LENGTH_LONG).show();
        } else if (Swelling_spinner.getSelectedItem().toString().equals("Swelling")) {
            ((TextView) Swelling_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Head Swelling", Toast.LENGTH_LONG).show();
        } else if (Eyes_right_spinner.getSelectedItem().toString().equals("Eyes Right")) {
            ((TextView) Eyes_right_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Eyes Right", Toast.LENGTH_LONG).show();
        } else if (Eyes_left_spinner.getSelectedItem().toString().equals("Eyes Left")) {
            ((TextView) Eyes_left_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Eyes Left ", Toast.LENGTH_LONG).show();
        } else if (Ears_right_spinner.getSelectedItem().toString().equals("Ears Right")) {
            ((TextView) Ears_right_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Ears Right Value", Toast.LENGTH_LONG).show();
        } else if (Ears_left_spinner.getSelectedItem().toString().equals("Ears Left")) {
            ((TextView) Ears_left_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Ears Left", Toast.LENGTH_LONG).show();
        } else if (Nose_spinner.getSelectedItem().toString().equals("Nose")) {
            ((TextView) Nose_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Nose value", Toast.LENGTH_LONG).show();
        } else if (Mouth_lips_spinner.getSelectedItem().toString().equals("Lips")) {
            ((TextView) Mouth_lips_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Lips data", Toast.LENGTH_LONG).show();
        } else if (Mouth_chin_spinner.getSelectedItem().toString().equals("Chin")) {
            ((TextView) Mouth_chin_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Chin data", Toast.LENGTH_LONG).show();
        } else if (facial_apeearance_spinner.getSelectedItem().toString().equals("Ovear all Facial Appearance")) {
            ((TextView) facial_apeearance_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Facial appearance data", Toast.LENGTH_LONG).show();
        } else if (Features_relatedTo_spinner.getSelectedItem().toString().equals("Features Relating To")) {
            ((TextView) Features_relatedTo_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select features relating to data", Toast.LENGTH_LONG).show();
        } else if (Neck_spinner.getSelectedItem().toString().equals("Neck")) {
            ((TextView) Neck_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Neck Data", Toast.LENGTH_LONG).show();
        } else if (Chest_spinner.getSelectedItem().toString().equals("Chest")) {
            ((TextView) Chest_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Chest data", Toast.LENGTH_LONG).show();
        } else if (Right_Uper_limb_spinner.getSelectedItem().toString().equals("Right Upper Limb")) {
            ((TextView) Right_Uper_limb_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select right upper limb data", Toast.LENGTH_LONG).show();
        } else if (Left_upper_limb_spinner.getSelectedItem().toString().equals("Left Upper Limb")) {
            ((TextView) Left_upper_limb_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Left upper limb data", Toast.LENGTH_LONG).show();
        } else if (Right_hand_spinner.getSelectedItem().toString().equals("Right Hand")) {
            ((TextView) Right_hand_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Right hand data", Toast.LENGTH_LONG).show();
        } else if (Left_hand_spinner.getSelectedItem().toString().equals("Left Hand")) {
            ((TextView) Left_hand_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select left hand data", Toast.LENGTH_LONG).show();
        } else if (Abdomen_spinner.getSelectedItem().toString().equals("Abdomen")) {
            ((TextView) Abdomen_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Abdomen data", Toast.LENGTH_LONG).show();
        } else if (Umbilicus_spinner.getSelectedItem().toString().equals("Umbilicus")) {
            ((TextView) Umbilicus_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Umbilicus data", Toast.LENGTH_LONG).show();
        } else if (Penis_spinner.getSelectedItem().toString().equals("Penis")) {
            ((TextView) Penis_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Penis spinner data", Toast.LENGTH_LONG).show();
        } else if (Scrotum_spinner.getSelectedItem().toString().equals("Scrotum")) {
            ((TextView) Scrotum_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select scrotum data", Toast.LENGTH_LONG).show();
        } else if (Female_Genitalia_spinner.getSelectedItem().toString().equals("Female Genitalia")) {
            ((TextView) Female_Genitalia_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Female Genitalia data", Toast.LENGTH_LONG).show();
        } else if (Legs_position_spinner.getSelectedItem().toString().equals("Legs position")) {
            ((TextView) Legs_position_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Legs position data", Toast.LENGTH_LONG).show();
        } else if (Right_lower_limb_spinner.getSelectedItem().toString().equals("Right Lower Limb")) {
            ((TextView) Right_lower_limb_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Right Lower Limb data", Toast.LENGTH_LONG).show();
        } else if (Left_lower_limb_spinner.getSelectedItem().toString().equals("Left Lower Limb")) {
            ((TextView) Left_lower_limb_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Left Lower Limb data", Toast.LENGTH_LONG).show();
        } else if (Right_foot_spinner.getSelectedItem().toString().equals("Right Foot")) {
            ((TextView) Right_foot_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Right Foot data", Toast.LENGTH_LONG).show();
        } else if (Left_foot_spinner.getSelectedItem().toString().equals("Left Foot")) {
            ((TextView) Left_foot_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select Left Foot data", Toast.LENGTH_LONG).show();
        } else if (Back_spinner.getSelectedItem().toString().equals("Back")) {
            ((TextView) Back_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "pease select Back data", Toast.LENGTH_LONG).show();
        } else if (Color_spinner.getSelectedItem().toString().equals("Color")) {
            ((TextView) Color_spinner.getSelectedView()).setError("Error message");
            Toast.makeText(MainActivity.this, "please select color data", Toast.LENGTH_LONG).show();
        } else {

           // Intent intent = new Intent(MainActivity.this, StorageActivity.class);
            //Create the bundle
          //  Bundle bundle = new Bundle();
//Add your data to bundle
          //  bundle.putString("id", id);

            //formbody to send data from android to python
            RequestBody formbody
                    = new FormBody.Builder()
                    .add("head","hello")
                    /*.add("head_Size", Head_Size)
                    .add("swelling", Swelling)
                    .add("eyes_right", Eyes_right)
                    .add("eyes_left", Eyes_left)
                    .add("ears_right", Ears_right)
                    .add("ears_left", Ears_left)
                    .add("nose", Nose)
                    .add("lips", Mouth_lips)
                    .add("chin", Mouth_chin)
                    .add("facial_appearance", facial_apeearance)
                    .add("features_relatedTo", Features_relatedTo)
                    .add("neck", Neck)
                    .add("chest", Chest)
                    .add("right_upper_limb", Right_Uper_limb)
                    .add("left_upper_limb", Left_upper_limb)
                    .add("right_hand", Right_hand)
                    .add("left_hand", Left_hand)
                    .add("abdomen", Abdomen)
                    .add("umbilicus", Umbilicus)
                    .add("penis", Penis)
                    .add("scrotum", Scrotum)
                    .add("female_genetalia", Female_Genitalia)
                    .add("legs_position", Legs_position)
                    .add("right_lower_limb", Right_lower_limb)
                    .add("left_lower_limb", Left_lower_limb)
                    .add("right_foot", Right_foot)
                    .add("left_foot", Left_foot)
                    .add("back", Back)
                    .add("color", Color)

*/
                    .build();

            // dummyText with a name 'sample'
           /* Request request = new Request.Builder().url("http://10.0.0.47:5000/debug")
                    .post(formbody)
                    .build();

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

            */
            // while building request
            // we give our form
            // as a parameter to post()
            Request request = new Request.Builder().url("http://10.0.0.47:5000//debug")
                    .post(formbody)
                    .build();


            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "server down", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(@NonNull okhttp3.Call call, @NonNull Response response) throws IOException {
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


















            //data base
        //    Data data=new Data(id,Head_shape,Head_size,Swelling,Eyes_right,Eyes_left,Ears_right,Ears_left,Nose,Mouth_lips,
          //          Mouth_chin,facial_apeearance,Features_relatedTo,Neck,Chest,Right_Uper_limb,Left_upper_limb,
         //           Right_hand,Left_hand,Abdomen,Umbilicus,Penis,Female_Genitalia,Legs_position,Right_lower_limb,Left_lower_limb
          //          ,Right_foot,Left_foot,Back,Color,Mr_number,ges_age,Head_Size,gender
           //         ,Length,Wt,HR,RR,Temp,Edit_by,Time,Age_days,Scrotum);
           // databaseReference.child(id).setValue(data);
          //  Data user = new Data(123, "John Doe");
           /* Call<Data> call = apiService.createuser(data);
            call.enqueue(new Callback<data>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }

            */



//Add the bundle to the intent
          //  intent.putExtras(bundle);
          //  startActivity(intent);

         //   Log.e("data", String.valueOf(data));
            Toast.makeText(MainActivity.this, "Your data has been uploaded", Toast.LENGTH_LONG).show();
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
              //  signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

   /* public void signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Intent i=new Intent(MainActivity.this, SignInFlow.class);
                        startActivity(i);
                    }
                });
        // [END auth_fui_signout]
    }

    */

}


