package com.example.Utpatur;

public class Position {
    private Integer positionId;
    private Double longitude;
    private Double latitude;
    private Integer altitude;
    private Integer routeId;

    public Position() {
    }

    public Position(Integer positionId, Double longitude, Double latitude, Integer altitude, Integer routeId) {
        this.positionId = positionId;
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
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
