package com.example.rcss_venue_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminEditVenue extends AppCompatActivity {

    private EditText editTextVenueName, editTextLocation, editTextCapacity;
    private Button buttonUpdateVenue, buttonDeleteVenue;
    private DatabaseReference databaseReference;
    private String venueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_venue);

        // Get Venue ID from Intent
        venueId = getIntent().getStringExtra("VENUE_ID");

        // Initialize UI components
        editTextVenueName = findViewById(R.id.editTextVenueNameEdit);
        editTextLocation = findViewById(R.id.editTextLocationEdit);
        editTextCapacity = findViewById(R.id.editTextCapacityEdit);
        buttonUpdateVenue = findViewById(R.id.buttonUpdateVenue);
        buttonDeleteVenue = findViewById(R.id.buttonDeleteVenue);

        // Initialize Firebase reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Venues").child(venueId);

        // Handle update button click
        buttonUpdateVenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateVenue();
            }
        });

        // Handle delete button click
        buttonDeleteVenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteVenue();
            }
        });
    }

    private void updateVenue() {
        String newVenueName = editTextVenueName.getText().toString().trim();
        String newLocation = editTextLocation.getText().toString().trim();
        String newCapacityStr = editTextCapacity.getText().toString().trim();

        if (newVenueName.isEmpty() || newLocation.isEmpty() || newCapacityStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int newCapacity = Integer.parseInt(newCapacityStr);

        // Update venue details in Firebase
        databaseReference.child("venueName").setValue(newVenueName);
        databaseReference.child("location").setValue(newLocation);
        databaseReference.child("capacity").setValue(newCapacity);

        Toast.makeText(this, "Venue Updated Successfully!", Toast.LENGTH_SHORT).show();
        finish(); // Close the activity
    }

    private void deleteVenue() {
        databaseReference.removeValue();
        Toast.makeText(this, "Venue Deleted Successfully!", Toast.LENGTH_SHORT).show();
        finish(); // Close the activity
    }
}
