package com.example.rcss_venue_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_Up extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword;
    private Button buttonRegister;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Authentication and Database
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users"); // Parent node in Realtime Database

        // Initialize views
        editTextName = findViewById(R.id.editTextText_name);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress_registration);
        editTextPassword = findViewById(R.id.editTextTextPassword_registration);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Set click listener for Register button
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Sign_Up.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(name, email, password);
                }
            }
        });
    }

    private void registerUser(String name, String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        if (firebaseUser != null) {
                            // Store additional user data in Realtime Database
                            String userId = firebaseUser.getUid();
                            UserModel user = new UserModel(name, email);
                            reference.child(userId).setValue(user);

                            Toast.makeText(Sign_Up.this, "Account Created!", Toast.LENGTH_SHORT).show();

                            // Redirect to SignInActivity after registration
                            Intent intent = new Intent(Sign_Up.this, Sign_In.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(Sign_Up.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    // User model class for Firebase Database
    public static class UserModel {
        public String name, email;

        public UserModel() {
            // Default constructor required for Firebase
        }

        public UserModel(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }
}
