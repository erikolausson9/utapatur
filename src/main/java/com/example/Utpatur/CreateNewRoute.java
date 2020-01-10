package com.example.Utpatur;

public class CreateNewRoute {
    //this class is only used as a means of transport of data from the HTML form to the MapController class when creating a new form

    private Integer routeId;
    private String routeName;
    private String routeType;
    private Integer height;
    private String difficulty;
    private Integer length;
    private Integer days;
    private Double hours;
    private String description;
    private String dateOfCompletion;
    private String routeCreated;
    private String routeLastUpdated;
    private Integer memberId;
    private String latitudes;
    private String longitudes;

    public CreateNewRoute(){

    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(String dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public String getRouteCreated() {
        return routeCreated;
    }

    public void setRouteCreated(String routeCreated) {
        this.routeCreated = routeCreated;
    }

    public String getRouteLastUpdated() {
        return routeLastUpdated;
    }

    public void setRouteLastUpdated(String routeLastUpdated) {
        this.routeLastUpdated = routeLastUpdated;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getLatitudes() {
        return latitudes;
    }

    public void setLatitudes(String latitudes) {
        this.latitudes = latitudes;
    }

    public String getLongitudes() {
        return longitudes;
    }

    public void setLongitudes(String longitudes) {
        this.longitudes = longitudes;
    }




}
