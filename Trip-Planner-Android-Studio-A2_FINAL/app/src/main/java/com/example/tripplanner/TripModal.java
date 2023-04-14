package com.example.tripplanner;


// Taken from https://www.geeksforgeeks.org/how-to-read-data-from-sqlite-database-in-android/
// and adapted to suit trip needs
public class TripModal {
    // variables for our trip start location, end location, date, method, and id.
    private String tripStart;
    private String tripEnd;
    private String tripDate;
    private String tripMethod;
    private int id;

    // creating getter and setter methods
    public String getTripStart() { return tripStart; }

    public void setTripStart(String tripStart)
    {
        this.tripStart = tripStart;
    }

    public String getTripEnd() {return tripEnd;}

    public void setTripEnd(String tripEnd)
    {
        this.tripEnd = tripEnd;
    }

    public String getTripDate() { return tripDate; }

    public void setTripDate(String tripDate)
    {
        this.tripDate = tripDate;
    }

    public String getTripMethod() { return tripMethod; }

    public void
    setTripMethod(String tripMethod)
    {
        this.tripMethod = tripMethod;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    // constructor
    public TripModal(String tripStart,
                       String tripEnd,
                       String tripDate,
                       String tripMethod)
    {
        this.tripStart = tripStart;
        this.tripEnd = tripEnd;
        this.tripDate = tripDate;
        this.tripMethod = tripMethod;
    }
}
