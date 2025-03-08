package com.example.rcss_venue_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Admin extends AppCompatActivity {

    Button buttonViewVenues, buttonAddVenue, buttonEditVenue, buttonDeleteVenue, buttonManageUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin); // Ensure this matches your XML filename

        // Initialize buttons
        buttonViewVenues = findViewById(R.id.buttonViewVenues);
        buttonAddVenue = findViewById(R.id.buttonAdminAddVenue);
        buttonEditVenue = findViewById(R.id.buttonEditVenue);
        buttonDeleteVenue = findViewById(R.id.buttonDeleteVenue);
        buttonManageUsers = findViewById(R.id.buttonManageUsers);

        // Set click listeners
        buttonViewVenues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, AdminViewVenues.class));
            }
        });

        buttonAddVenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, AdminAddVenue.class));
            }
        });

        buttonEditVenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, AdminEditVenue.class));
            }
        });

        buttonDeleteVenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, AdminDeleteVenue.class));
            }
        });

        buttonManageUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, Admin.class));
            }
        });
    }
}
