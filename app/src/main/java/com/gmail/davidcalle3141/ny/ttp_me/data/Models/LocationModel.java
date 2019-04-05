package com.gmail.davidcalle3141.ny.ttp_me.data.Models;

public class LocationModel {
    private String longitude;
    private String latitude;
    private String distance;
    public LocationModel(String latitude, String longitude, String distance){
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
