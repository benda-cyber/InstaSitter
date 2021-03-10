package com.example.instasitter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText loginEmail;
    EditText loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mAuth = FirebaseAuth.getInstance();

        loginEmail = findViewById(R.id.loginEmailAddress);
        loginPassword = findViewById(R.id.loginPassword);
        }
    //    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }

    public void toRegisterPage(View view) {
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }

    public void signIn(View view) {
        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginPage.this, "Login worked.",
                                    Toast.LENGTH_SHORT).show();

                            String uid = user.getUid();
                            Intent intentLogin = new Intent(LoginPage.this, MainActivity.class);
                            intentLogin.putExtra("uidkey",uid);
                            startActivity(intentLogin);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginPage.this, "Login failed.",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }
}