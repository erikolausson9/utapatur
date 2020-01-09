package com.example.Utpatur;

public class Bookmark {
    private Integer bookmarkId;
    private Integer memberId;
    private Integer routeId;

    public Bookmark() {
    }

    public Bookmark(Integer bookmarkId, Integer memberId, Integer routeId) {
        this.bookmarkId = bookmarkId;
        this.memberId = memberId;
        this.routeId = routeId;
    }

    public Integer getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(Integer bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }
}
