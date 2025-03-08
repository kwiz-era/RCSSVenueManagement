package com.example.rcss_venue_management;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAddVenue extends AppCompatActivity {

    private EditText editTextVenueName, editTextLocation, editTextCapacity;
    private Button buttonAddVenue;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_venue); // Ensure this matches your XML filename

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Venues");

        // Initialize UI elements
        editTextVenueName = findViewById(R.id.editTextAdminVenueName);
        editTextLocation = findViewById(R.id.editTextAdminLocation);
        editTextCapacity = findViewById(R.id.editTextAdminCapacity);
        buttonAddVenue = findViewById(R.id.buttonAdminAddVenue);

        // Set button click listener
        buttonAddVenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVenueToFirebase();
            }
        });
    }

    private void addVenueToFirebase() {
        String venueName = editTextVenueName.getText().toString().trim();
        String location = editTextLocation.getText().toString().trim();
        String capacityStr = editTextCapacity.getText().toString().trim();

        // Validate input fields
        if (TextUtils.isEmpty(venueName) || TextUtils.isEmpty(location) || TextUtils.isEmpty(capacityStr)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int capacity = Integer.parseInt(capacityStr);

        // Generate unique venue ID
        String venueId = databaseReference.push().getKey();

        // Create a venue object
        Venue venue = new Venue(venueId, venueName, location, capacity);

        // Store in Firebase Database
        databaseReference.child(venueId).setValue(venue)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(AdminAddVenue.this, "Venue Added Successfully!", Toast.LENGTH_SHORT).show();
                    clearFields();
                })
                .addOnFailureListener(e -> Toast.makeText(AdminAddVenue.this, "Failed to add venue", Toast.LENGTH_SHORT).show());
    }

    private void clearFields() {
        editTextVenueName.setText("");
        editTextLocation.setText("");
        editTextCapacity.setText("");
    }
}
