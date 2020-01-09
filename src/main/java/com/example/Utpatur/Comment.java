package com.example.Utpatur;

public class Comment {
    private Integer commentId;
    private String commentText;
    private Integer routeId;
    private Integer memberId;

    public Comment() {
    }

    public Comment(Integer commentId, String commentText, Integer routeId, Integer memberId) {
        this.commentId = commentId;
        this.commentText = commentText;
        this.routeId = routeId;
        this.memberId = memberId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
