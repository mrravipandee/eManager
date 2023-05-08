package com.mrravipande.emanager.Management;

public class EventDataInDB {
    String eventTitle;
    String eventDesciption;
    String eventLocation;
    String eventDate;
    String eventCollege;
    String key;
    String date;
    String time;
    String image;

    public EventDataInDB(String image, String eventTitle, String eventDesciption, String eventLocation, String eventDate, String eventCollege, String key, String date, String time, String eLink) {
        this.image = image;
        this.eventTitle = eventTitle;
        this.eventDesciption = eventDesciption;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventCollege = eventCollege;
        this.key = key;
        this.date = date;
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDesciption() {
        return eventDesciption;
    }

    public void setEventDesciption(String eventDesciption) {
        this.eventDesciption = eventDesciption;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventCollege() {
        return eventCollege;
    }

    public void setEventCollege(String eventCollege) {
        this.eventCollege = eventCollege;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
