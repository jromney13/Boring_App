package com.example.finalproject_joshromney.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Activity {
    private String activity;
    private String participants;
    private String price;
    private String link;
    @PrimaryKey(autoGenerate = true)
    private int key;

    public Activity(String activity, String participants, String price, String link, int key) {
        this.activity = activity;
        this.participants = participants;
        this.price = price;
        this.link = link;
        this.key = key;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activity='" + activity + '\'' +
                ", participants='" + participants + '\'' +
                ", price='" + price + '\'' +
                ", link='" + link + '\'' +
                ", key=" + key +
                '}';
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
