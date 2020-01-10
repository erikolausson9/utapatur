package com.example.Utpatur;

public class Position {
    private Integer positionId;
    private double longitude;
    private double latitude;
    private Integer altitude;
    private Integer routeId;

    public Position() {
    }

    public Position(double longitude, double latitude, Integer altitude, Integer routeId) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.routeId = routeId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }
}
