package com.example.smsa;



public class CollegeEvent {
    private String eventName;
    private String eventDescription;

    public CollegeEvent(String eventName, String eventDescription) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }
}
