package com.example.instasitter.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.instasitter.R;
import com.example.instasitter.classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText name;
    EditText familyName;
    EditText dateOfBirth;
    EditText phone;
    EditText address;
    EditText email;
    EditText password;
    String uid;
    FirebaseDatabase database;
    Switch isServiceProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.registerName);
        familyName = findViewById(R.id.registerFamilyName);
        dateOfBirth = findViewById(R.id.registerDateOfBirth);
        phone = findViewById(R.id.registerPhone);
        address = findViewById(R.id.registerAddress);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        database = FirebaseDatabase.getInstance();
        isServiceProvider = (Switch) findViewById(R.id.serviceProvider);
//        SwitchCompat.setOnCheckedChangeListener((buttonView, isChecked)






    }

    public void cancelRegistration(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void createAccount(View view) {
        if(!Validatename() | !ValidatefamilyName() | !Validateaddress() | !Validateemail() | !Validatepassword() | !Validatephone()){
            Toast.makeText(RegisterPage.this, "Registration failed.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        String nameStr = name.getText().toString();
        String familyNameStr = familyName.getText().toString();
        String dateOfBirthStr = dateOfBirth.getText().toString();
        String phoneStr = phone.getText().toString();
        String addressStr = address.getText().toString();
        String emailStr = email.getText().toString();
        String passwordStr = password.getText().toString();


        mAuth.createUserWithEmailAndPassword(emailStr, passwordStr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                            uid = user.getUid();



                            User u = new User(nameStr, familyNameStr, dateOfBirthStr, phoneStr, addressStr, emailStr, passwordStr);



                            if(isServiceProvider.isChecked())
                            {
                                DatabaseReference myRef = database.getReference("service_providers").child(uid);
                                myRef.setValue(u);

                                Intent intentExtraPage = new Intent(RegisterPage.this, ServiceProviderExtra.class);
                                intentExtraPage.putExtra("keyuid",uid);
                                startActivity(intentExtraPage);
                            }
                            else {

                                DatabaseReference myRef = database.getReference("users").child(uid);
                                myRef.setValue(u);

                                Toast.makeText(RegisterPage.this, "Registered successfully.",
                                        Toast.LENGTH_SHORT).show();
                                Intent intentLogin = new Intent(RegisterPage.this, MainActivity.class);
                                intentLogin.putExtra("keyuid",uid);
                                startActivity(intentLogin);
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterPage.this, "Registration failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }



    private boolean Validatename(){
        String val = name.getText().toString().trim();

        if(val.isEmpty()){
            name.setError("Field can not be empty");
            return false;
        }
        else{
            name.setError(null);
            return true;
        }


    }

    private boolean ValidatefamilyName(){
        String val = familyName.getText().toString().trim();

        if(val.isEmpty()){
            familyName.setError("Field can not be empty");
            return false;
        }
        else{
            familyName.setError(null);
            return true;
        }


    }

    private boolean Validateaddress(){
        String val = address.getText().toString().trim();

        if(val.isEmpty()){
            address.setError("Field can not be empty");
            return false;
        }
        else{
            address.setError(null);
            return true;
        }


    }

    private boolean Validateemail(){
        String val = email.getText().toString().trim();
        String checkspaces = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            email.setError("Field can not be empty");
            return false;
        }else if (!val.matches(checkspaces)){
            email.setError("Invalid Email!");
            return false;
        }
        else{
            email.setError(null);
            return true;
        }


    }

    private boolean Validatepassword(){
        String val = password.getText().toString().trim();


        if(val.isEmpty()){
            password.setError("Field can not be empty");
            return false;

        }
        else{
            password.setError(null);
            return true;
        }


    }

    private boolean Validatephone(){
        String val = phone.getText().toString().trim();


        if(val.isEmpty()){
            phone.setError("Field can not be empty");
            return false;

        }
        else{
            phone.setError(null);
            return true;
        }


    }



}