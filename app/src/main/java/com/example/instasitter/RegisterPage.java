package com.example.instasitter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
    }

    public void cancelRegistration(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void createAccount(View view) {

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


                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("users").child(uid);

                            User u = new User(nameStr, familyNameStr, dateOfBirthStr, phoneStr, addressStr, emailStr,passwordStr);
                            myRef.setValue(u);

                            Toast.makeText(RegisterPage.this, "Registered successfully.",
                                    Toast.LENGTH_SHORT).show();

                            Intent intentLogin = new Intent(RegisterPage.this, MainActivity.class);
                            intentLogin.putExtra("keyuid",uid);
                            startActivity(intentLogin);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterPage.this, "Registration failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


}