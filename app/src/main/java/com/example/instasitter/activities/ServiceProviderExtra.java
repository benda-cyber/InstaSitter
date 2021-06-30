package com.example.instasitter.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.instasitter.R;
import com.example.instasitter.classes.ServiceProvider;
import com.example.instasitter.classes.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

public class ServiceProviderExtra extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE = 1;
    private ImageView profilePicture, idPicture;
    private Button chooseProfilePicButton, chooseIdPicButton, registerButton, uploadPicsButton;
    private Uri profileImgUri;
    private Uri idImgUri;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private String uid;
    private EditText idNum;
    private Switch isDogwalker;
    private Switch isBabysitter;
    private User user;
    private ServiceProvider serviceUser;
    private int flag =-1;
    private int picsFlag = -1;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_extra);

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        storageRef = storage.getReference();

        profilePicture = (ImageView) findViewById(R.id.registerProfilePicture);
        idPicture = (ImageView) findViewById(R.id.registerIdPicture);

        chooseProfilePicButton = (Button) findViewById(R.id.registerProfilePictureChooserBottun);
        chooseIdPicButton = (Button) findViewById(R.id.registerIdPictureChooserBottun);

        uploadPicsButton = (Button) findViewById(R.id.uploadPicturesButton);

        registerButton = (Button) findViewById(R.id.serviceProviderRegisterButton);
        idNum = findViewById(R.id.registerIdNumber);
        isDogwalker = (Switch) findViewById(R.id.dogwalker);
        isBabysitter = (Switch) findViewById(R.id.babysitter);

        if (getIntent().hasExtra("keyuid")) {

            uid = getIntent().getStringExtra("keyuid");

        }
        if (getIntent().hasExtra("keyuser")){

            Intent intent = getIntent();
            user  = (User) intent.getSerializableExtra("keyuser");
            serviceUser = new ServiceProvider(user);

        }
        chooseProfilePicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGetContent.launch("image/*");

                if(profilePicture!=null){
                    flag = 0;
                }

            }
        });
        chooseIdPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGetContent.launch("image/*");

                if(idPicture!=null){
                    flag = 1;
                }

            }
        });

        uploadPicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((flag!=1)||(profileImgUri==null)||(idImgUri==null)){
                    Toast.makeText(ServiceProviderExtra.this, "Please choose your photos",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                fileUploader();

            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(picsFlag!=1){
                    Toast.makeText(ServiceProviderExtra.this, "Please upload your photos",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if((idNum!=null) && (idNum.length()==9)){
                    serviceUser.setIdNum(idNum.getText().toString());

                }
                else{
                    Toast.makeText(ServiceProviderExtra.this, "Please enter a valid 9 digit ID number",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!isBabysitter.isChecked() && !isDogwalker.isChecked()){
                    Toast.makeText(ServiceProviderExtra.this, "Please pick at least one service",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(isBabysitter.isChecked()) {
                    serviceUser.setBabysitter(true);
                }

                if(isDogwalker.isChecked()) {
                    serviceUser.setDogwalker(true);
                }


                DatabaseReference myRef = database.getReference("service_providers").child(uid);
                myRef.setValue(serviceUser);

                Intent intent = new Intent(ServiceProviderExtra.this, MainActivity.class);
                Toast.makeText(ServiceProviderExtra.this, "Registration Successful",
                        Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }

    private String getExtension(Uri uri){

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }

    private void fileUploader() {
        if ((profileImgUri != null) && (idImgUri != null)) {


            ProgressDialog pd = new ProgressDialog(this);
            pd.setMessage("Uploading Images");
            pd.show();
            StorageReference profilePicRef = storageRef.child(uid).child("profile_picture." + getExtension(profileImgUri));
            StorageReference idPicRef = storageRef.child(uid).child("id_picture." + getExtension(idImgUri));

            profilePicRef.putFile(profileImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    profilePicRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            serviceUser.setProfilePic(uri.toString());

                            picsFlag = 0;
                        }
                    });
                }
            });

            idPicRef.putFile(idImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    idPicRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            picsFlag = 1;
                        }
                    });
                }
            });
            pd.dismiss();
            if(picsFlag == 1){
                Toast.makeText(ServiceProviderExtra.this, "Images Uploaded Successfully",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }




    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            if ((result.getPath() != null) && (result != null)) {
                if (flag == 0) {

                    profileImgUri = result;
                    profilePicture.setImageURI(profileImgUri);

                }
                if (flag == 1) {

                    idImgUri = result;
                    idPicture.setImageURI(idImgUri);

                }
            }
            else{
                Toast.makeText(ServiceProviderExtra.this, "Please choose an image",
                        Toast.LENGTH_SHORT).show();
            }
        }
    });


    @Override
    public void onClick(View v) {

    }
}
