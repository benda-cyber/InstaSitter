package com.example.instasitter.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;

import com.example.instasitter.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ServiceProviderExtra extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE = 1;
    ImageView profilePicture, idPicture;
    Button chooseProfilePicButton, chooseIdPicButton, registerButton;
    Uri profileImgUri;
    Uri idImgUri;
    Uri showImageUri;
    StorageReference mStorageRef;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_extra);

        mStorageRef = FirebaseStorage.getInstance().getReference("Images");

        profilePicture = (ImageView) findViewById(R.id.registerProfilePicture);
        idPicture = (ImageView) findViewById(R.id.registerIdPicture);

        chooseProfilePicButton = (Button) findViewById(R.id.registerProfilePictureUploadBottun);
        chooseIdPicButton = (Button) findViewById(R.id.registerIdPictureUploadBottun);
        registerButton = (Button) findViewById(R.id.serviceProviderRegisterButton);

        chooseProfilePicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileChooser();
            }
        });
        chooseIdPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileChooser();

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileUploader();
            }
        });

        if (getIntent().hasExtra("keyuid")) {

            uid = getIntent().getStringExtra("keyuid");

        }
    }

    private String getExtension(Uri uri){

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }

    private void fileUploader(){

//        StorageReference storageReference = mStorageRef.child(uid).child();
        StorageReference profilePicRef = mStorageRef.child(uid).child("profilePic." + getExtension(profileImgUri));
        StorageReference idPicRef = mStorageRef.child(uid).child("idPic." + getExtension(idImgUri));

        profilePicRef.putFile(profileImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                profilePicRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String profilePicUrl = String.valueOf(uri);
//                        StoreLink(profilePicUrl);
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
//                        String profilePicUrl = String.valueOf(uri);
//                        StoreLink(profilePicUrl);
                    }
                });
            }
        });
    }

//    private void StoreLink(String Url) {
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child()
//    }

    private void fileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data!=null && data.getData()!=null){


            Uri fileUri = data.getData();
            profilePicture.setImageURI(fileUri);


        }
    }

    @Override
    public void onClick(View v) {

    }
}
