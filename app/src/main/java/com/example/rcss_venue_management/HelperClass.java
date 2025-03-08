package com.example.rcss_venue_management;

public class HelperClass {
    private String name;
    private String email;
    private String password;

    // Empty constructor required for Firebase
    public HelperClass() {
    }

    // Constructor to initialize values
    public HelperClass(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class Venue {
    private String venueId;
    private String venueName;
    private String location;
    private int capacity;

    // Empty constructor required for Firebase
    public Venue() {
    }

    // Constructor to initialize values
    public Venue(String venueId, String venueName, String location, int capacity) {
        this.venueId = venueId;
        this.venueName = venueName;
        this.location = location;
        this.capacity = capacity;
    }

    // Getters
    public String getVenueId() {
        return venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    // Setters
    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
