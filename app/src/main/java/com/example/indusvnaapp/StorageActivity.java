package com.example.indusvnaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.indusvnaapp.Login.SignInFlow;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StorageActivity extends AppCompatActivity {
    private String id;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;
    //image buttons ids
    private Button Front_pic_Button,Side_pic_Button,Head_pic_Button, Right_Limbs_pic_Button,Left_limb_pic_button,back_pic_button,Face_pic_Button;
    private Button Image_upload;
    //imageview
    private ImageView front_pic_imageview,side_pic_imageview,head_pic_imageview, Right_limbs_pic_imageview,left_limbs_pic_imageview,face_pic_imageview,back_imageview;
    private static final int CAMERA_REQUEST = 1888;
    //public final static int PICK_PHOTO_CODE = 1046;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    // request code
    private final int PICK_IMAGE_GALLERY_FRONT = 22;
    private final int PICK_IMAGE_CAMERA_FRONT = 33;
    private final int PICK_IMAGE_GALLERY_SIDE = 44;
    private final int PICK_IMAGE_CAMERA_SIDE = 55;
    private final int PICK_IMAGE_GALLERY_HEAD=66;
    private final int PICK_IMAGE_CAMERA_HEAD=77;
    private final int PICK_IMAGE_GALLERY_LIMBS=88;
    private final int PICK_IMAGE_CAMERA_LIMBS=99;
    private final int PICK_IMAGE_CAMERA_FACE=11;
    private final int PICK_IMAGE_GALLERY_FACE=12;
    private final int PICK_IMAGE_GALLERY_LEFT_LIMB=31;
    private final int PICK_IMAGE_CAMERA_LEFT_LIMB=41;
    private final int PICK_IMAGE_GALLERY_BACK=51;
    private final int PICK_IMAGE_CAMERA_BACK=61;

    //flags for checking if paricular image button is pressed
    private Boolean face_bool,head_bool,limbs_bool,side_bool,front_bool,left_limb_bool,back_bool;


    // / instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;
    StorageReference ref;
    // Uri indicates, where the image will be picked from
    private Uri filePathFront,filePathHead,filePathLimbs,filePathFace,filePathSide,filePathLeftLimb,filePathBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        //getting id data from bundle
        Bundle bundle = getIntent().getExtras();
        id= bundle.getString("id");
        Log.e("id",id);
        //asign boolean values
        side_bool=false;
        front_bool=false;
        face_bool=false;
        head_bool=false;
        limbs_bool=false;
        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        ref= storageReference.child(id);

        //getting Button Ids
        Front_pic_Button=findViewById(R.id.front_pic_button);
        Side_pic_Button=findViewById(R.id.side_pic_button);
        Head_pic_Button=findViewById(R.id.head_pic_button);
        Right_Limbs_pic_Button =findViewById(R.id.right_limbs_pic_button);
        Face_pic_Button=findViewById(R.id.face_pic_button);
        Left_limb_pic_button=findViewById(R.id.left_limbs_pic_button);
        back_pic_button=findViewById(R.id.back_pic_button);
        //image upload button
        Image_upload=findViewById(R.id.upload_images);
        //Imageview ids
        front_pic_imageview=findViewById(R.id.front_imageview);
        side_pic_imageview=findViewById(R.id.side_image);
        head_pic_imageview=findViewById(R.id.head_imageview);
        Right_limbs_pic_imageview =findViewById(R.id.right_limbs_imageview);
        face_pic_imageview=findViewById(R.id.face_imageview);
        left_limbs_pic_imageview=findViewById(R.id.left_limbs_imageview);
        back_imageview=findViewById(R.id.back_imageview);

        Front_pic_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                front_bool=true;
                pickImage_Front();
            }
        });
        Side_pic_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                side_bool=true;
                pickImage_Side();
            }
        });
        Head_pic_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                head_bool=true;
                pickImage_Head();

            }
        });
        Right_Limbs_pic_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                limbs_bool=true;
                pickImage_Right_Limbs();
            }
        });
        Left_limb_pic_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage_Left_limb();
            }
        });
        back_pic_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage_back();
            }
        });
        Face_pic_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                face_bool=true;
                pickImage_Face();
            }
        });
        Image_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Upload_Images();
            }
        });



    }
    private void pickImage_Front(){
        try {
           // PackageManager pm = getPackageManager();
           // int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
            if(checkAndRequestPermissions(StorageActivity.this)){
                final CharSequence[] options2 = {"Take Photo", "Choose From Gallery", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(StorageActivity.this);
                builder.setTitle("Select Option");
                builder.setItems(options2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options2[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent,PICK_IMAGE_CAMERA_FRONT);
                        } else if (options2[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto,PICK_IMAGE_GALLERY_FRONT);
                        } else if (options2[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        ///CAPTURE IMAGE FROM CAMERA



    }
    private void pickImage_Side(){
        try {
          //  PackageManager pm = getPackageManager();
           // int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
           // if (hasPerm == PackageManager.PERMISSION_GRANTED) {
            if(checkAndRequestPermissions(StorageActivity.this)){
                final CharSequence[] options2 = {"Take Photo", "Choose From Gallery", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(StorageActivity.this);
                builder.setTitle("Select Option");
                builder.setItems(options2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options2[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA_SIDE);
                        } else if (options2[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY_SIDE);
                        } else if (options2[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        ///CAPTURE IMAGE FROM CAMERA



    }
    private void pickImage_Head(){
        try {
          //  PackageManager pm = getPackageManager();
            //int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
           // if (hasPerm == PackageManager.PERMISSION_GRANTED) {
            if(checkAndRequestPermissions(StorageActivity.this)){
                final CharSequence[] options2 = {"Take Photo", "Choose From Gallery", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(StorageActivity.this);
                builder.setTitle("Select Option");
                builder.setItems(options2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options2[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA_HEAD);
                        } else if (options2[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY_HEAD);
                        } else if (options2[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        ///CAPTURE IMAGE FROM CAMERA



    }
    private void pickImage_Right_Limbs(){
        try {
          //  PackageManager pm = getPackageManager();
         //   int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
          //  if (hasPerm == PackageManager.PERMISSION_GRANTED) {
            if(checkAndRequestPermissions(StorageActivity.this)){
                final CharSequence[] options2 = {"Take Photo", "Choose From Gallery", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(StorageActivity.this);
                builder.setTitle("Select Option");
                builder.setItems(options2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options2[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA_LIMBS);
                        } else if (options2[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY_LIMBS);
                        } else if (options2[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        ///CAPTURE IMAGE FROM CAMERA



    }
    private void pickImage_Left_limb(){
        try {
            // PackageManager pm = getPackageManager();
            // int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
            //  if (hasPerm == PackageManager.PERMISSION_GRANTED) {
            if(checkAndRequestPermissions(StorageActivity.this)){
                final CharSequence[] options2 = {"Take Photo", "Choose From Gallery", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(StorageActivity.this);
                builder.setTitle("Select Option");
                builder.setItems(options2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options2[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA_LEFT_LIMB);
                        } else if (options2[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY_LEFT_LIMB);
                        } else if (options2[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    private void pickImage_back(){
        try {
            // PackageManager pm = getPackageManager();
            // int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
            //  if (hasPerm == PackageManager.PERMISSION_GRANTED) {
            if(checkAndRequestPermissions(StorageActivity.this)){
                final CharSequence[] options2 = {"Take Photo", "Choose From Gallery", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(StorageActivity.this);
                builder.setTitle("Select Option");
                builder.setItems(options2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options2[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA_BACK);
                        } else if (options2[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY_BACK);
                        } else if (options2[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    private void pickImage_Face() {
        try {
           // PackageManager pm = getPackageManager();
           // int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
          //  if (hasPerm == PackageManager.PERMISSION_GRANTED) {
            if(checkAndRequestPermissions(StorageActivity.this)){
                final CharSequence[] options2 = {"Take Photo", "Choose From Gallery", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(StorageActivity.this);
                builder.setTitle("Select Option");
                builder.setItems(options2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options2[item].equals("Take Photo")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA_FACE);
                        } else if (options2[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY_FACE);
                        } else if (options2[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_GALLERY_FRONT
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePathFront = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePathFront);
                front_pic_imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();

            }
        } else if (requestCode == PICK_IMAGE_CAMERA_FRONT && resultCode == Activity.RESULT_OK) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            front_pic_imageview.setImageBitmap(bitmap);
        } else if (requestCode == PICK_IMAGE_GALLERY_SIDE
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePathSide = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePathSide);
                side_pic_imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();

            }
        } else if (requestCode == PICK_IMAGE_CAMERA_SIDE && resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            side_pic_imageview.setImageBitmap(bitmap);

        } else if (requestCode == PICK_IMAGE_GALLERY_HEAD
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePathHead = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePathHead);
                head_pic_imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();

            }
        } else if (requestCode == PICK_IMAGE_CAMERA_HEAD && resultCode == Activity.RESULT_OK) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            head_pic_imageview.setImageBitmap(bitmap);
        } else if (requestCode == PICK_IMAGE_GALLERY_LIMBS
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePathLimbs = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePathLimbs);
                Right_limbs_pic_imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();

            }
        } else if (requestCode == PICK_IMAGE_CAMERA_LIMBS && resultCode == Activity.RESULT_OK) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Right_limbs_pic_imageview.setImageBitmap(bitmap);
        } else if (requestCode == PICK_IMAGE_GALLERY_FACE
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePathFace = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePathFace);
                face_pic_imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();

            }
        } else if (requestCode == PICK_IMAGE_CAMERA_FACE && resultCode == Activity.RESULT_OK) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            face_pic_imageview.setImageBitmap(bitmap);
        }
        else if (requestCode == PICK_IMAGE_GALLERY_LEFT_LIMB
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePathLeftLimb = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePathLeftLimb);
                left_limbs_pic_imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();

            }
        }
        else if (requestCode ==PICK_IMAGE_CAMERA_LEFT_LIMB && resultCode == Activity.RESULT_OK) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            left_limbs_pic_imageview.setImageBitmap(bitmap);
        }
        else if (requestCode == PICK_IMAGE_GALLERY_BACK
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
         filePathBack = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePathBack);
                back_imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();

            }
        }
        else if (requestCode ==PICK_IMAGE_CAMERA_BACK && resultCode == Activity.RESULT_OK) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
           back_imageview.setImageBitmap(bitmap);
        }
    }



    private void Upload_Images() {
        StorageReference mountainsRef = ref.child(id + "_front".toString());
        ;

// Create a reference to 'images/mountains.jpg'
        // StorageReference mountainImagesRef = storageReference.child("images/");
        if (front_pic_imageview.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_baseline_person_24).getConstantState()) {

            Toast.makeText(StorageActivity.this, "please select all Images", Toast.LENGTH_LONG).show();
        }
        // Get the data from an ImageView as bytes
        else {
            front_pic_imageview.setDrawingCacheEnabled(true);
            face_pic_imageview.buildDrawingCache();
            Bitmap bitmap = ((BitmapDrawable) front_pic_imageview.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data1 = baos.toByteArray();
            UploadTask uploadTask = mountainsRef.putBytes(data1);
        }
        //upload side image via camera
        StorageReference SideImageRef = ref.child(id + "_side".toString());
        ;
        if (side_pic_imageview.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_baseline_person_24).getConstantState()) {

            Toast.makeText(StorageActivity.this, "please select all Images", Toast.LENGTH_LONG).show();
        } else {
            //StorageReference mountainImagesRef2 = storageReference.child("images/");
            side_pic_imageview.setDrawingCacheEnabled(true);
            side_pic_imageview.buildDrawingCache();
            Bitmap bitmap1 = ((BitmapDrawable) side_pic_imageview.getDrawable()).getBitmap();
            ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
            bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, baos1);
            byte[] data11 = baos1.toByteArray();

            UploadTask uploadSideImage = SideImageRef.putBytes(data11);
        }

        //uplod head image via camera
        StorageReference HeadImageRef = ref.child(id + "_head".toString());
        ;
        if (head_pic_imageview.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_baseline_person_24).getConstantState()) {

            Toast.makeText(StorageActivity.this, "please select all Images", Toast.LENGTH_LONG).show();
        } else {
            //StorageReference mountainImagesRef2 = storageReference.child("images/");
            head_pic_imageview.setDrawingCacheEnabled(true);
            head_pic_imageview.buildDrawingCache();
            Bitmap bitmaphead = ((BitmapDrawable) head_pic_imageview.getDrawable()).getBitmap();
            ByteArrayOutputStream headboas = new ByteArrayOutputStream();
            bitmaphead.compress(Bitmap.CompressFormat.JPEG, 100, headboas);
            byte[] datahead = headboas.toByteArray();

            UploadTask uploadHeadImage = HeadImageRef.putBytes(datahead);
        }
        //upload Limbs images via camera
        StorageReference LimbsImageRef = ref.child(id + "_rightLimb".toString());
        ;
        if (Right_limbs_pic_imageview.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_baseline_person_24).getConstantState()) {

            Toast.makeText(StorageActivity.this, "please select all Image", Toast.LENGTH_LONG).show();
        } else {
            //StorageReference mountainImagesRef2 = storageReference.child("images/");
            Right_limbs_pic_imageview.setDrawingCacheEnabled(true);
            Right_limbs_pic_imageview.buildDrawingCache();
            Bitmap bitmaplimbs = ((BitmapDrawable) Right_limbs_pic_imageview.getDrawable()).getBitmap();
            ByteArrayOutputStream baoslimbs = new ByteArrayOutputStream();
            bitmaplimbs.compress(Bitmap.CompressFormat.JPEG, 100, baoslimbs);
            byte[] datalimbs = baoslimbs.toByteArray();

            UploadTask uploadLimbsImage = LimbsImageRef.putBytes(datalimbs);
        }
        //upload face images via camera
        StorageReference FaceImageRef = ref.child(id + "_face".toString());
        ;
        if (face_pic_imageview.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_baseline_person_24).getConstantState()) {

            Toast.makeText(StorageActivity.this, "please select Front Image", Toast.LENGTH_LONG).show();
        } else {
            //StorageReference mountainImagesRef2 = storageReference.child("images/");
            //face_pic_imageview.setDrawingCacheEnabled(true);
            //face_pic_imageview.buildDrawingCache();
            Bitmap bitmapface = ((BitmapDrawable) face_pic_imageview.getDrawable()).getBitmap();
            ByteArrayOutputStream baosface = new ByteArrayOutputStream();
            bitmapface.compress(Bitmap.CompressFormat.JPEG, 100, baosface);
            byte[] dataface = baosface.toByteArray();

            UploadTask uploadFaceImage = FaceImageRef.putBytes(dataface);
        }
        //upload left limb images via camera
        StorageReference leftlimImageRef = ref.child(id + "_leftlimb".toString());
        ;
        if (left_limbs_pic_imageview.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_baseline_person_24).getConstantState()) {

            Toast.makeText(StorageActivity.this, "please select all Images", Toast.LENGTH_LONG).show();
        } else {
            //StorageReference mountainImagesRef2 = storageReference.child("images/");
            //face_pic_imageview.setDrawingCacheEnabled(true);
            //face_pic_imageview.buildDrawingCache();
            Bitmap bitmapleftlimb = ((BitmapDrawable) left_limbs_pic_imageview.getDrawable()).getBitmap();
            ByteArrayOutputStream baosleftlimb = new ByteArrayOutputStream();
            bitmapleftlimb.compress(Bitmap.CompressFormat.JPEG, 100, baosleftlimb);
            byte[] dataleftlimb = baosleftlimb.toByteArray();

            UploadTask uploadLeftLimbImage = leftlimImageRef.putBytes(dataleftlimb);
        }
        //upload face images via camera
        StorageReference BackImageRef = ref.child(id + "_back".toString());
        ;
        if (back_imageview.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.ic_baseline_person_24).getConstantState()) {

            Toast.makeText(StorageActivity.this, "please select Front Image", Toast.LENGTH_LONG).show();
        } else {
            //StorageReference mountainImagesRef2 = storageReference.child("images/");
            //face_pic_imageview.setDrawingCacheEnabled(true);
            //face_pic_imageview.buildDrawingCache();
            Bitmap bitmapback = ((BitmapDrawable) back_imageview.getDrawable()).getBitmap();
            ByteArrayOutputStream baosback = new ByteArrayOutputStream();
            bitmapback.compress(Bitmap.CompressFormat.JPEG, 100, baosback);
            byte[] databack = baosback.toByteArray();

            UploadTask uploadabackImage = BackImageRef.putBytes(databack);
        }


        if (filePathFront != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref1
                    = ref
                    .child(id + "_front".toString());

            // adding listeners on upload
            // or failure of image
            ref1.putFile(filePathFront)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(StorageActivity.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(StorageActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            });
        }
        //upload second image from gallery
        else if (filePathSide != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref12
                    = ref
                    .child(id + "_side".toString());

            // adding listeners on upload
            // or failure of image
            ref12.putFile(filePathSide)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(StorageActivity.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(StorageActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            });
        }


        //upload  head image from gallery
        else if (filePathHead != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref123
                    = ref
                    .child(

                            id + "_head".toString());

            // adding listeners on upload
            // or failure of image
            ref123.putFile(filePathHead)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(StorageActivity.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(StorageActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            });
        }
        //upload Limbs image from gallery
        else if (filePathLimbs != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref445
                    = ref
                    .child(

                            id + "_rightLimb".toString());

            // adding listeners on upload
            // or failure of image
            ref445.putFile(filePathLimbs)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(StorageActivity.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(StorageActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            });
        }
        //upload face image from gallery
        else if (filePathFace != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref78
                    = ref
                    .child(

                            id + "_face".toString());

            // adding listeners on upload
            // or failure of image
            ref78.putFile(filePathFace)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(StorageActivity.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(StorageActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            });
        }
        //gallery left limb image upload code
        else if (filePathLeftLimb != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference refleftlimb
                    = ref
                    .child(

                            id + "_leftLimb".toString());

            // adding listeners on upload
            // or failure of image
            refleftlimb.putFile(filePathLeftLimb)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(StorageActivity.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(StorageActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            });
        }

        //back image gallery
        else if (filePathBack != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference refback
                    = ref
                    .child(

                            id + "_head".toString());

            // adding listeners on upload
            // or failure of image
            refback.putFile(filePathBack)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(StorageActivity.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(StorageActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            });
        }
        if (left_limbs_pic_imageview.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.ic_baseline_person_24).getConstantState() && Right_limbs_pic_imageview.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.ic_baseline_person_24).getConstantState()
            && front_pic_imageview.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.ic_baseline_person_24).getConstantState() &&
                face_pic_imageview.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.ic_baseline_person_24).getConstantState() &&
                head_pic_imageview.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.ic_baseline_person_24).getConstantState() &&
               back_imageview.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.ic_baseline_person_24).getConstantState() &&
                side_pic_imageview.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.ic_baseline_person_24).getConstantState())

                {
                Intent i=new Intent(StorageActivity.this,EditTextActivity.class);
                startActivity(i);


            }

        }




    public static boolean checkAndRequestPermissions(final Activity context) {
        int ExtstorePermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (ExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded
                    .add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded
                            .toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS:
                if (ContextCompat.checkSelfPermission(StorageActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),
                            "IndusVNA Requires Access to Camara.", Toast.LENGTH_SHORT)
                            .show();
                    finish();
                } else if (ContextCompat.checkSelfPermission(StorageActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),
                            "IndusVNA Requires Access to Your Storage.",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    if(face_bool==true) {
                        pickImage_Face();
                    }
                    else if(front_bool==true) {
                        pickImage_Front();
                    }
                    else if(head_bool==true) {
                        pickImage_Head();
                    }
                    else if(limbs_bool==true) {
                        pickImage_Right_Limbs();
                    }
                    else if(side_bool==true) {
                        pickImage_Side();
                    }
                    else if(left_limb_bool==true){
                        pickImage_Left_limb();
                    }
                    else if(back_bool==true){
                        pickImage_back();
                    }
                }
                break;
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
                        Intent i=new Intent(StorageActivity.this, SignInFlow.class);
                        startActivity(i);
                    }
                });
        // [END auth_fui_signout]
    }

}















