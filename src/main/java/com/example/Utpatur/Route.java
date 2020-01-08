package com.example.Utpatur;

public class Route {
    private Integer routeId;
    private String routeName;
    private String routeType;
    private Integer height;
    private String difficulty;
    private Integer length;
    private String duration;
    private String season;
    private String description;
    private String dateOfCompletion;
    private String routeCreated;
    private String routeLastUpdated;
    private Integer memberId;

    public Route(){
    }

    public Route(Integer routeId, String routeName, String routeType, Integer height, String difficulty, Integer length, String duration, String season, String description, String dateOfCompletion, String routeCreated, String routeLastUpdated, Integer memberId) {
        this.routeId = routeId;
        this.routeName = routeName;
        this.routeType = routeType;
        this.height = height;
        this.difficulty = difficulty;
        this.length = length;
        this.duration = duration;
        this.season = season;
        this.description = description;
        this.dateOfCompletion = dateOfCompletion;
        this.routeCreated = routeCreated;
        this.routeLastUpdated = routeLastUpdated;
        this.memberId = memberId;
    }

    public int getRouteId() {
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
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
}
